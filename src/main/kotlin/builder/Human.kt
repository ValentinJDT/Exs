package builder

import java.awt.Color

class Human private constructor(val name: String, val height: Int, val legs: Int, val arms: Int, val eyes: Color, override val gender: Gender): LivingEntity {

    data class Builder(
        var name: String, var height: Int = 180, var legs: Int = 2, var arms: Int = 2, var eyes: Color = Color.GRAY, var gender: Gender = Gender.BOY
    ) {
        fun name(name: String) = apply { this.name = name }
        fun height(height: Int) = apply { this.height = height }
        fun legs(legs: Int) = apply { this.legs = legs }
        fun arms(arms: Int) = apply { this.arms = arms }
        fun eyes(eyes: Color) = apply { this.eyes = eyes }
        fun gender(gender: Gender) = apply { this.gender = gender }
        fun build() = Human(name, height, legs, arms, eyes, gender)
    }
}
