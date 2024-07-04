package builder

import java.awt.Color

class Human private constructor(val name: String, val height: Int, val legs: Int, val arms: Int, val eyes: Color, override val gender: Gender): LivingEntity {

    data class Builder(
        val name: String, val height: Int = 180, val legs: Int = 2, val arms: Int = 2, val eyes: Color = Color.GRAY, val gender: Gender = Gender.BOY
    ) {
        fun name(name: String) = run { copy(name = name) }
        fun height(height: Int) = run { copy(height = height) }
        fun legs(legs: Int) = run { copy(legs = legs) }
        fun arms(arms: Int) = run { copy(arms = arms) }
        fun eyes(eyes: Color) = run { copy(eyes = eyes) }
        fun gender(gender: Gender) = run { copy(gender = gender) }
        fun build() = Human(name, height, legs, arms, eyes, gender)
    }

    override fun toString(): String = "Human(name='$name', height=$height, legs=$legs, arms=$arms, eyes=$eyes, gender=$gender)"
}
