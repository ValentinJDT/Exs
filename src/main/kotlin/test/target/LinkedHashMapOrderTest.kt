package test.target

import linkedhashmaporder.MyRequest
import test.Test
import test.TestClass
import test.assertEq

class LinkedHashMapOrderTest: TestClass() {

    @Test
    fun `linked hash map order`() {
        val request = MyRequest("name:asc,age:desc,grid:asc")

        val sortParams = request.getSortParams()

        assertEq("name", sortParams?.keys?.first())

        assertEq("age", sortParams?.keys?.toList()?.get(1))
    }

}
