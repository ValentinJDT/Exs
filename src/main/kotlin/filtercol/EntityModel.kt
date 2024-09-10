package filtercol

import java.math.BigInteger

data class EntityModel(
    val name: String,
    val enabled: Boolean,
    val intN: Int,
    val bigIntN: BigInteger
)
