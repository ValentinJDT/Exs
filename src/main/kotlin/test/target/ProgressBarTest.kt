package test.target

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.tongfei.progressbar.ProgressBar
import me.tongfei.progressbar.ProgressBarBuilder
import me.tongfei.progressbar.ProgressBarStyle
import test.Test
import test.TestClass
import kotlin.random.Random

class ProgressBarTest : TestClass() {

    @Test
    fun `render progress bar`() = runBlocking {

        val collection: List<String> = listOf(
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y",
            "Z"
        )

        println("Writing ththth")

        val style = ProgressBarBuilder().setTaskName("Collection").setStyle(ProgressBarStyle.COLORFUL_UNICODE_BAR)

        for (content in ProgressBar.wrap(collection, style)) {
           1+1
            val bool = Random.nextBoolean()
            if(bool) delay(5000)
            else delay(2500)
        }

    }

}