package generator

import java.time.Instant
import java.util.stream.Collectors

class MyNumberGenerator: Generator<String, Long> {

    override fun execute(value: String): Long {
        val list : List<Int> = value.toCharArray().toList().stream().map { it.code }.collect(Collectors.toList())
        return list.sum() * Instant.now().toEpochMilli()
    }



}