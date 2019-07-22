package shapes.feature.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import shapes.feature.domain.ShapeEntity

class ShapesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private val path = Path()

    var shapes = emptyList<ShapeEntity>()
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shapes.onEach { drawShape(it, canvas!!) }
    }

    private fun drawShape(shape: ShapeEntity, canvas: Canvas) {
        when (shape) {
            is ShapeEntity.Circle ->
                drawCircle(canvas, shape.center.first, shape.center.second, getRadius())

            is ShapeEntity.Square ->
                drawSquare(canvas, shape.center.first, shape.center.second, getRadius())

            is ShapeEntity.Triangle ->
                drawTriangle(canvas, shape.center.first, shape.center.second, getRadius())
        }
    }

    private fun getRadius(): Float {
        // TODO calculate radius based on screen size
        return 100f
    }

    private fun drawCircle(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        canvas.drawCircle(cx, cy, radius, paint)
    }

    private fun drawSquare(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        val left = cx - radius
        val top = cy - radius
        val right = cx + radius
        val bottom = cy + radius

        canvas.drawRect(left, top, right, bottom, paint)
    }

    private fun drawTriangle(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        val topX = cx
        val topY = cy - radius

        val leftX = cx - radius
        val leftY = cy + radius

        val rightX = cx + radius
        val rightY = cy + radius

        path.moveTo(topX, topY)
        path.lineTo(rightX, rightY)
        path.lineTo(leftX, leftY)
        path.lineTo(topX, topY)

        canvas.drawPath(path, paint)
    }
}