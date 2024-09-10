package test.target

import test.Test
import test.TestClass
import test.assertFalse
import test.assertTrue

class ContainsTest : TestClass() {

    @Test
    fun `contains all exactly`() {
        val tags = listOf("tag1", "tag2", "tag3")
        val includTags = listOf("tag1", "tag2")
        assertTrue(tags.containsAll(includTags))
    }

    @Test
    fun `contains all not exactly`() {
        val tags = listOf("tag1", "tag2", "tag3")
        val includTags = listOf("tag1", "tag2", "tag4")
        assertFalse(tags.containsAll(includTags))
    }

    @Test
    fun `contains all not very exactly but yes`() {
        val tags = listOf("tag1", "tag2", "tag3", "tag:oui123")
        val includTags = listOf("tag2", "tag:oui")

        assertTrue(
            tags.containsAll(includTags.filter { !it.contains(":") }) &&
                    includTags.filter { it.contains(":") }
                        .all {
                            tags.any { tag ->
                                tag.contains(it)
                            }
                        }
        )
    }

}
