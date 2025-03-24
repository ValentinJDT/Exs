package compare.json

import com.iknova.gl.structdata4.core.IDataManager
import com.iknova.gl.structdata4.core.TData
import com.iknova.gl.structdata4.core.modules.EmptyModule
import com.iknova.gl.structdata4.core.modules.IDataModule
import com.iknova.gl.structdata4.core.modules.plus
import com.iknova.gl.structdata4.core.newDataManager
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*

public val dataManager: IDataManager = newDataManager(
    ServiceLoader.load(IDataModule::class.java).fold<IDataModule, IDataModule>(
        EmptyModule
    ) { acc, mod -> acc + mod })

val serializer = Json {
    serializersModule = dataManager.serializersModule
}

@Serializable
data class ComparableClass(
    var name: String,
    var age: Int?,
    val metadata: MutableMap<String, TData> = mutableMapOf<String, TData>(),
    @Contextual
    val properties: HashMap<String, String> = HashMap(),
) {

    operator fun get(key: String) = properties[key]

    operator fun set(key: String, value: String) {
        properties[key] = value
    }

    fun setMetadata(vararg params: Pair<String, Any?>) {
        if (params.isEmpty()) return
        mapOf(*params).mapValues { dataManager.typed(it.value as Any) }.forEach { (key, value) ->
            metadata.put(key, value)
        }
    }

}