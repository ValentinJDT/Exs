package heritage


interface MyIt<M>

class Her : MyIt<Any>

inline fun <reified T : Her> test(): String {
    return T::class.java.name
}
