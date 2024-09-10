package readtestfiles

import java.io.File
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths


val directoryDir = File("Y:/Tester/profiles")
val directoryUri = URI("file://cirlyad03/atg/Tester/profiles/")

fun getMachines() = runCatching {
        directoryDir.listFiles()
            ?.let {
                it.filter {
                    it.path.contains("LogfileForTestsPerMonth_") && it.path.endsWith(".csv")
                }.map {
                    it.path.split("_")[1]
                }.distinct()
            }
    }.getOrNull()

//fun loadCsv(machine: String, year: String, month: String) = runCatching {
//    directoryUri.resolve("LogfileForTestsPerMonth_${machine}_${year}_${month}.csv").toURL().openStream()
//        .use { String(it.readAllBytes()) }
//}.getOrThrow()

fun loadCsv(machine: String, year: String, month: String) = runCatching {
    val chemin = Paths.get(directoryUri.resolve("LogfileForTestsPerMonth_${machine}_${year}_${month}.csv"))

    return@runCatching Files.readString(chemin)
}.getOrNull()
