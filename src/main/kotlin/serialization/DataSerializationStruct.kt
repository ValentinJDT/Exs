package serialization

import com.iknova.gl.structdata4.core.IDataManager
import com.iknova.gl.structdata4.core.TypedDataValue
import com.iknova.gl.structdata4.core.modules.EmptyModule
import com.iknova.gl.structdata4.core.modules.IDataModule
import com.iknova.gl.structdata4.core.modules.plus
import com.iknova.gl.structdata4.core.newDataManager
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.plus
import java.util.ServiceLoader


class DataSerializationStruct {

    private companion object {
        val dataManager: IDataManager = newDataManager(
            ServiceLoader.load(IDataModule::class.java).fold<IDataModule, IDataModule>(
                EmptyModule
            ) { acc, mod -> acc + mod }).also {
            runBlocking {
                it.awaitInitialization()
            }
        }
    }


    val serializer = Json {
        serializersModule += dataManager.serializersModule
    }

    inline fun <reified T> encodeToByteArray(data: T) = serializer.encodeToString(data).toByteArray()
    inline fun <reified T> decodeToClass(data: ByteArray) = serializer.decodeFromString<T>(String(data))
}

enum class EnumToSerialize {
    A, B, C, D
}

@Serializable
data class MasterSerialize(
    val a: TypedDataValue<ToSerialize, *>,
    val b: TypedDataValue<ToSerialize, *>,
    val c: TypedDataValue<ToSerialize, *>,
    val d: TypedDataValue<ToSerialize, *>
)

@Serializable
data class ToSerialize(val a: Int, val b: String, val c: EnumToSerialize, val data: TypedDataValue<String, *>)