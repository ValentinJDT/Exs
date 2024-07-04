package test

fun <T> assertNotNull(value: T?): T = if(value == null) { throw AssertionError("null") } else { value }
fun assertNull(value: Any?): Unit = if(value != null) { throw AssertionError("null") } else { }
fun assertTrue(boolean: Boolean): Unit = if(!boolean) { throw AssertionError("false") } else { }
fun assertFalse(boolean: Boolean): Unit = if(boolean) { throw AssertionError("true") } else { }
fun assertEq(arg1: Any?, arg2: Any?): Unit = if(arg1 != arg2) { throw AssertionError("Not equals") } else { }
fun assertNotEq(arg1: Any?, arg2: Any?): Unit = if(arg1 == arg2) { throw AssertionError("Equals") } else { }


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Test(val throwError: Boolean = false, val order: Int = 100)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Qualifier(val value: String)

interface BeanContainer {
    fun beans(): Map<String, Any>
}

class StaticData {
    private val data = mutableMapOf<String, Any>()

    operator fun set(key: String, value: Any) {
        data[key] = value
    }

    operator fun <T> get(key: String): T? = data[key] as T?

    override fun toString(): String = this::class.simpleName + "=" + data.toString()
}

class TypedStaticData<T> {
    private val data = mutableMapOf<String, T>()

    operator fun set(key: String, value: T) {
        data[key] = value
    }

    operator fun get(key: String): T? = data[key]

    override fun toString(): String = this::class.simpleName + "=" + data.toString()
}

abstract class TestClass: BeanContainer {
    init {
        val beans = beans() + mapOf(StaticData::class.java.name to StaticData())

        val tests = mutableMapOf<String, Boolean>()

        val methods = this::class.java.declaredMethods.filter { it.isAnnotationPresent(Test::class.java) }.sortedBy { it.getAnnotation(Test::class.java).order }

        for(method in methods) {
            val throwError = method.getDeclaredAnnotation(Test::class.java).throwError

            val params = mutableListOf<Any>()

            method.parameters.forEach {
                if(it.isAnnotationPresent(Qualifier::class.java)) {
                    beans[it.getAnnotation(Qualifier::class.java).value]?.let { params.add(it) }
                    return@forEach
                }

                beans[it.type.name]?.let { params.add(it) }
            }

            try {
                method.invoke(this, *params.toTypedArray())

                if(throwError) tests["`${method.name}`() : Unfortunely passed"] = false

            } catch(exception: Exception) {
                if(!throwError) {
                    tests["`${method.name}`() : ${exception.cause?.message}"] = false
                }
            }
        }

        System.out.println(" ")

        if(methods.isEmpty()) {
            System.err.println("${this::class.java.name} : No test methods found")

        } else if(tests.filter { !it.value }.isEmpty()) {
            println("${this::class.java.name} : ${methods.size}/${methods.size} tests passed")

        } else {
            System.err.println("${this::class.java.name} : ${methods.size - tests.filter { !it.value }.size}/${methods.size} tests passed")
        }

        tests.forEach {
            if(it.value) {
                System.out.println("> ${it.key}")
            } else {
                System.err.println("> ${it.key}")
            }
        }
    }

    override fun beans(): Map<String, Any> {
        return emptyMap()
    }
}
