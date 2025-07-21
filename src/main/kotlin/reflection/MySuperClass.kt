package reflection

import java.lang.reflect.Method
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class MySuperClass(
    val name: String,
    val age: Int
) {

    private val superClassData: SuperClassData = SuperClassData("reading")

    fun greet() {
        println("Hello, my name is $name and I am $age years old.")
    }

    fun getData(): SuperClassData = superClassData
}


class SuperClassData(
    private val test: String,
) {
    fun showHobby() {
        println("I enjoy $test.")
    }

    fun getTest(): String = test
}

fun  MySuperClass.getTestValue(): String {
    val clazz1: Class<MySuperClass> = this.javaClass
    val methodA: Method = clazz1.getMethod("getData")
    val clazz2 = methodA.invoke(this)
    val methodB: Method = clazz2.javaClass.getMethod("getTest")
    return methodB.invoke(clazz2) as String
}

fun <T> execute(instance: Any, execution: String): T {
    try {
        // Diviser la chaîne d'exécution en parties
        val methodCalls = execution.split(".")
        var result: Any = instance

        // Parcourir chaque appel de méthode
        for (methodCall in methodCalls) {
            // Extraire le nom de la méthode
            val methodName = methodCall.substringBefore("(")
            // Obtenir la méthode
            val method = result::class.memberFunctions.find { it.name == methodName }
                ?: throw NoSuchMethodException(methodName)
            // Invoquer la méthode
            result = method.call(result)!!
        }

        // Retourner le résultat avec le type spécifié
        @Suppress("UNCHECKED_CAST")
        return result as T
    } catch (e: Exception) {
        throw RuntimeException("Failed to execute method chain: $execution", e)
    }
}


fun <T> executeBis(instance: Any, execution: String): T {
    var result: Any = instance
    val parts = execution.split(".")

    for (part in parts) {
        // Supprimer les parenthèses si c'est un appel de méthode
        val name = if (part.contains("(")) part.substringBefore("(") else part

        val clazz = result::class
        val member = clazz.memberFunctions.find { it.name == name } ?: clazz.memberProperties.find { it.name == name }
        ?: throw NoSuchElementException("Neither method nor property found: $name")

        result = when (member) {
            is KFunction<*> -> member.call(result)!!
            is KProperty<*> -> {
                member.isAccessible = true
                member.getter.call(result)!!
            }
            else -> throw IllegalStateException("Unsupported member type")
        }
    }

    @Suppress("UNCHECKED_CAST")
    return result as T
}