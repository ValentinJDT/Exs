package provider

import java.util.*

class Provider(val values: ArrayList<out Any>) {

    inline fun <reified T> get(): T {
        for (obj in values) {
            if (obj is T) {
                return obj
            }
        }
        throw IllegalStateException()
    }

    inline fun <reified T> getOrNull(): T? {
        for (obj in values) {
            if (obj is T) {
                return obj
            }
        }
        return null
    }

    inline fun <reified T> getOrElse(value: T): T {
        for (obj in values) {
            if (obj is T) {
                return obj
            }
        }
        return value
    }

}
