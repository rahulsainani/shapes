package shapes.feature.domain

data class ShapeDomainEntity(
    val id: Int,
    val type: Type
) {
    enum class Type {
        SQUARE, CIRCLE, TRIANGLE
    }
}
