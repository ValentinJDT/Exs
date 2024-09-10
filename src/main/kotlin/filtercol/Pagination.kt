package filtercol

/** Pagination information for a request. */
interface IPagination {
    val state: String?
    val pageNumber: Int
    val pageSize: Int
    val sort: String?
}

/** Pagination information for a request with a date range. */
interface IDateRangePagination : IPagination {
    val from: String
    val to: String
}

/** Pagination props for a response. */
data class PaginationProps(
    val currentPageNumber: Int = 1,
    val totalPages: Int,
    val totalItems: Int,
    val itemsPerPage: Int,
    val nextPage: String? = if (totalPages == 0) null else if (currentPageNumber < totalPages) "${currentPageNumber + 1}" else null,
    val previousPage: String? = if (currentPageNumber - 1 <= 0) null else if (currentPageNumber - 1 > totalPages) "$totalPages" else "${currentPageNumber - 1}"
)

/** Items and pagination props for a response. */
interface IPaginatedList<T> {
    /** List of items to send. */
    val items: List<T>

    /** Pagination props for the list. */
    val pagination: PaginationProps
}

data class PaginatedList<T>(
    override val items: List<T> = emptyList(),
    override val pagination: PaginationProps
) : IPaginatedList<T> {
    fun <K> map(mapItems: (T) -> K): PaginatedList<K> =
        PaginatedList(items = items.map { mapItems(it) }, pagination)
}

val EMPTY_PAGINATED_LIST =
    PaginatedList<Nothing>(pagination = PaginationProps(totalPages = 1, itemsPerPage = 10, totalItems = 0))

fun IPagination.getSortParams(): LinkedHashMap<String, String> {
    val map = linkedMapOf<String, String>()
    sort?.let {
        val args = it.split(",")

        args.forEach {
            val pair = it.split(":")
            if (pair.size == 2) {
                map[pair[0]] = pair[1]
            }
        }
    }
    return map
}
