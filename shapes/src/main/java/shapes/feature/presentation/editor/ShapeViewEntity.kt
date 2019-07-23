package shapes.feature.presentation.editor

data class ShapeViewEntity(
    val gridPosition: Int,
    val type: Type
) {
    enum class Type {
        SQUARE, CIRCLE, TRIANGLE
    }
}