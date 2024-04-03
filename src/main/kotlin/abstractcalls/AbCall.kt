package abstractcalls

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.timer

interface ICall {
    var circle: Int
    val timer: Timer?
    suspend fun call()
}

abstract class AbstractCall : ICall {

    override var circle: Int = 0
    override var timer: Timer? = null


    init {
        timer = timer("CallTimer", false, 0, 1000) {
            CoroutineScope(Dispatchers.IO).launch {
                call()
                circle++

                if (circle == 10) {
                    timer?.cancel()
                }
            }
        }
    }
}

class Call1 : AbstractCall() {
    override suspend fun call() {
        println("Call1")
    }
}

class Call2 : AbstractCall() {
    override suspend fun call() {
        println("Call2")
    }
}
