package inlinerecursive

import java.util.*
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType

interface DTO


object DTOMaker {
    fun <T : DTO> fakeList(clazz: KClass<T>, max: Int, seed: Int? = null): List<T> {
        return (1..max).map {
            fake(clazz, seed)
        }
    }

    fun <T : DTO> fake(clazz: KClass<T>, seed: Int? = null): T {

        val random = seed?.let { Random(it) } ?: Random.Default

        val parameters = mutableListOf<Any?>()

        clazz.primaryConstructor!!.parameters.forEach {

            val filtered =
                it.type.toString().replace("?", "").replace("<.*>".toRegex(), "").replace("/\\*.*\\*/".toRegex(), "")
                    .trim()
            when {
                filtered == String::class.qualifiedName -> parameters.add(
                    listOf("A name", "An other name", "A word", "An other word", "Something")[random.nextInt(5)]
                )
                filtered == Int::class.qualifiedName -> parameters.add(random.nextInt())
                filtered == Boolean::class.qualifiedName -> parameters.add(random.nextBoolean())
                filtered == Date::class.qualifiedName -> parameters.add(Date())

                filtered.startsWith(List::class.qualifiedName!!) -> {
                    val listType = it.type.arguments[0].type!!.classifier as KClass<*>

                    when(listType) {
                        String::class -> parameters.add(List(random.nextInt(10)) { UUID.randomUUID().toString() })
                        Int::class -> parameters.add(List(random.nextInt(10)) { random.nextInt() })
                        Boolean::class -> parameters.add(List(random.nextInt(10)) { random.nextBoolean() })
                        Date::class -> parameters.add(List(random.nextInt(10)) { Date() })
                        else -> {
                            if(clazz.supertypes.contains(DTO::class.starProjectedType)) {
                                parameters.add(fakeList(listType as KClass<DTO>, random.nextInt(10)))
                            } else {
                                parameters.add(emptyList<Any>())
                            }
                        }
                    }
                }

                else -> {
                    if(clazz.supertypes.contains(DTO::class.starProjectedType)) {
                        parameters.add(fake(Class.forName(filtered).kotlin as KClass<DTO>))
                    } else {
                        println("Unknown type: $filtered")
                        parameters.add(null)
                    }
                }
            }
        }

        return clazz.primaryConstructor!!.call(*parameters.toTypedArray())
    }
}

data class MyDTO(val value: String) : DTO

data class CustomSecondClassDTO(val value: String, val another: ThirdClassDTO) : DTO

data class ThirdClassDTO(val value: String) : DTO
data class CustomClassDTO(val value: String, val secondValue: CustomSecondClassDTO, val myList: List<MyDTO>) : DTO
