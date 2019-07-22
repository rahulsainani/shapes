package shapes.feature.domain

data class ShapeEntityList(
    val shapes: List<ShapeEntity>
)

sealed class ShapeEntity {
    abstract val id: Int
    abstract val center: Pair<Float, Float>

    data class Square(
        override val id: Int,
        override val center: Pair<Float, Float>
    ) : ShapeEntity()

    data class Circle(
        override val id: Int,
        override val center: Pair<Float, Float>
    ) : ShapeEntity()

    data class Triangle(
        override val id: Int,
        override val center: Pair<Float, Float>
    ) : ShapeEntity()
}