package outputsave

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

data class Person(
    val name: String,
    val age: Int,
    val metadata: Map<String, String>,
): Serializable

class OutputSave {
    companion object {
        fun <T> export(value: T) {
            val fileOutputStream = FileOutputStream("outputfile.txt")
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(value)
            objectOutputStream.flush()
            objectOutputStream.close()
        }

        fun <T> import(): T {
            val fileInputStream = FileInputStream("outputfile.txt")
            val objectInputStream = ObjectInputStream(fileInputStream)
            val value = objectInputStream.readObject() as T
            objectInputStream.close()
            return value
        }
    }
}