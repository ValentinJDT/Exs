package test.target

import test.*
import url.Path

class PathTest: TestClass() {

    @Test
    fun `find correct page`() {
        val query = "/api/default"

        val path = Path.getRoute(query)

        assertEq(path, Path.GLOBAL)
    }

    @Test
    fun `params exist`() {
        val query = "/api/uhzdioqzd-54qz8d/personal"

        val path = Path.getRoute(query)

        assertTrue(path.extractArgs(query).isNotEmpty())
    }

    @Test
    fun `find params`() {
        val query = "/api/uhzdioqzd-54qz8d/personal"

        val path = Path.getRoute(query)
        val param = path.extractArgs(query).get("uuid")

        assertTrue(param == "uhzdioqzd-54qz8d")
    }

    @Test(true)
    fun `can't find correct page`() {
        val query = "/api/zdqzd"

        val path = Path.getRoute(query)

        assertNotEq(path, Path.NOT_FOUND)
    }
}