package linkedhashmaporder

interface IRequest {
    val sort: String
    fun getSortParams(): LinkedHashMap<String, String>? {
        val map = linkedMapOf<String, String>()

        sort?.let {
            val split = it.split(",")
            split.forEach {
                val pair = it.split(":")
                map[pair[0]] = pair[1]
            }
        }

        return map
    }
}


data class MyRequest(override val sort: String): IRequest
