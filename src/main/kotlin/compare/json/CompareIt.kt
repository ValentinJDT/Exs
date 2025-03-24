package compare.json

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer
import kotlin.reflect.full.createType

@Serializable
data class CompareResult(val old: MutableMap<String, JsonElement> = mutableMapOf<String, JsonElement>(), val new: MutableMap<String, JsonElement> = mutableMapOf<String, JsonElement>() )

class Comparation<Model> {

    fun compare(a: Model, b: Model): CompareResult {
        val sa = serializer(a!!::class.createType())
        val sb = serializer(b!!::class.createType())

        val ae = Json.encodeToJsonElement(sa, a) as JsonObject
        val be = Json.encodeToJsonElement(sb, b) as JsonObject

        val result = CompareResult()

        ae.entries.forEach { (key, value) ->
            if (be[key] != value) {
                result.old[key] = value
                result.new[key] = be[key] ?: JsonNull
            }
        }

        return result
    }

}


