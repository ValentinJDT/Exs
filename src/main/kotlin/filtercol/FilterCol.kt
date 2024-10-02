package filtercol

import kotlin.math.ceil

/** Paginate the items of a Collection. */
inline fun <reified T : Any> Collection<T>.paginate(params: IPagination, filter: (T, IPagination) -> Boolean? = { _, _ -> true }): PaginatedList<T> {
    // Apply custom filter provided by the caller
    val filteredItems = this.filter { item ->
        filter(item, params) ?: true
    }

    // Extract and apply sorting parameters
    val sortParams = params.getSortParams().mapNotNull { (key, value) ->

        T::class.members.firstOrNull { it.name == key } ?: return@mapNotNull null

        when (value) {
            "ASC" -> key to Comparator<T> { a, b ->
                val first = a::class.members.first { it.name == key }.call(a)
                val second = b::class.members.first { it.name == key }.call(b)

                if(first is Number && second is Number) {
                    first.toDouble().compareTo(second.toDouble())
                } else {
                    first.toString().compareTo(second.toString())
                }
            }
            "DESC" -> key to Comparator<T> { a, b ->
                val first = a::class.members.first { it.name == key }.call(a)
                val second = b::class.members.first { it.name == key }.call(b)

                if(first is Number && second is Number) {
                    second.toDouble().compareTo(first.toDouble())
                } else {
                    second.toString().compareTo(first.toString())
                }
            }
            else -> null
        }
    }.toMap()

    // Apply sorting to the filtered items
    val sortedItems = filteredItems.sortedWith(Comparator { a, b ->
        sortParams.entries.fold(0) { acc, (key, comparator) ->
            if (acc != 0) acc else comparator.compare(a, b)
        }
    })

    val totalItems = sortedItems.size
    val totalPages = ceil(totalItems.toDouble() / params.pageSize).toInt()

    if (params.pageNumber <= 0) {
        return PaginatedList(
            pagination = PaginationProps(params.pageNumber, totalPages, totalItems, params.pageSize, nextPage = "1")
        )
    }

    // Calculate skip and limit for pagination
    val skip = (params.pageNumber - 1) * params.pageSize
    val paginatedList = sortedItems.drop(skip).take(params.pageSize)

    // Return the paginated list with pagination properties
    return PaginatedList(
        paginatedList,
        PaginationProps(params.pageNumber, totalPages, totalItems, params.pageSize)
    )
}
