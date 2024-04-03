package strfun

operator fun String.invoke(params: String) {
    params(params+"0")
}