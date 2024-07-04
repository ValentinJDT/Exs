package fileprinter

import java.io.PrintWriter
import java.nio.file.Files.newBufferedWriter
import kotlin.io.path.Path
import kotlin.io.path.createFile

fun writeFile(file: String, block: PrintWriter.() -> Unit) = Path(file).createFile().also { PrintWriter(newBufferedWriter(it)).use(block) }.toFile()
