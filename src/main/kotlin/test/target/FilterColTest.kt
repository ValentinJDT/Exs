package test.target

import filtercol.EntityModel
import filtercol.IPagination
import filtercol.paginate
import test.Test
import test.TestClass

class FilterColTest : TestClass() {

    @Test
    fun `filter columns`() {

        val myCollection = listOf(
            EntityModel("test1", false, 2, 1.toBigInteger()),
            EntityModel("test4", true, 4, 9.toBigInteger()),
            EntityModel("test2", true, 1, 1.toBigInteger()),
            EntityModel("test3", true, 3, 2.toBigInteger()),
            EntityModel("test5", false, 8, 6.toBigInteger())
        )

        val paginatedA = myCollection.paginate(object: IPagination {
            override val state: String? = null
            override val pageNumber: Int = 1
            override val pageSize: Int = 10
            override val sort: String = "UwU:ASC"
        }) { item, pagination -> pagination.state == null || item.enabled == pagination.state.toBoolean()}

        paginatedA.items.forEach {
            println(it)
        }
    }

}
