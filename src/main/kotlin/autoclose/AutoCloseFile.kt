package autoclose

import java.nio.file.Files
import java.nio.file.Path

class TempFile(prefix: String = "Test", suffix: String = ".txt") : AutoCloseable {


    val path: Path = Files.createTempFile(prefix, suffix)

    val content: String
        get() = path.toFile().readText()

    val exists: Boolean
        get() = Files.exists(path)

    fun writeLine(line: String): Unit = path.toFile().writeText(line)

    override fun close(): Unit = run { Files.deleteIfExists(path) }
}

fun tempFile(prefix: String = "Test", suffix: String = ".txt", block: TempFile.() -> Unit): TempFile = TempFile(prefix, suffix).use { block; it }