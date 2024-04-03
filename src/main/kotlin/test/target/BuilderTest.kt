package test.target

import builder.Gender
import builder.Human
import builder.LivingEntity
import test.Test
import test.TestClass
import test.assertNotNull
import test.assertTrue
import java.awt.Color

class BuilderTest: TestClass() {

    @Test
    fun `init human builder`() {
        assertNotNull(Human.Builder("John"))
    }

    @Test
    fun `create human with the builder`() {

        val human = Human.Builder("John").build()

        assertTrue(human is LivingEntity)
    }

    @Test
    fun `verify human properties`() {
        val human: Human = Human.Builder("Louna")
            .name("M")
            .arms(2)
            .legs(1)
            .height(165)
            .eyes(Color.CYAN)
            .gender(Gender.GIRL)
            .build()

        assertTrue(human.arms == 2)
        assertTrue(human.legs == 1)
        assertTrue(human.height == 165)
        assertTrue(human.eyes == Color.CYAN)
        assertTrue(human.gender == Gender.GIRL)
    }
}