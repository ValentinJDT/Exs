package inlinerecursive


//inline fun <reified T: Any> recursive(value: T): T {
//
//    if(value is String && value.startsWith("s")) {
//        return hideRecursive(value.substring(1) as T)
//    }
//
//    return value
//}
//
//inline fun <reified T: Any> hideRecursive(value: T): T = recursive(value)


fun <T: Any> testRecursive(value: T): T {
    if(value is String && value.startsWith("s")) {
        return testHideRecursive(value.substring(1) as T)
    }
    return value
}

fun <T: Any> testHideRecursive(value: T): T = testRecursive(value)
