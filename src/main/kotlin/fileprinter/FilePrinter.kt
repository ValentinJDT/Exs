package fileprinter

import java.io.PrintWriter
import java.nio.file.Files.newBufferedWriter
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.exists

fun writeFile(file: String, block: PrintWriter.() -> Unit) =
    (if(Path(file).exists()) Path(file) else Path(file).createFile()).also { PrintWriter(newBufferedWriter(it)).use(block) }.toFile()
