package test.target

import kotlinx.coroutines.runBlocking
import readtestfiles.getMachines
import readtestfiles.loadCsv
import test.Test
import test.TestClass

class FileTestersTest: TestClass() {

    @Test
    fun `read files`() {
        val monthSuffixes = (1..12).map { it.toString() }
        val yearSuffixes = (2000..3000).map { it.toString() }

        runBlocking {
            getMachines()?.forEach { machine ->
                yearSuffixes.forEach { y ->
                    monthSuffixes.forEach { m ->
                        loadCsv(machine, y, m)?.let { content ->
                            println(content)
                        }
                    }
                }
            }

        }
    }

}