package shapes.feature.presentation.editor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.min
import shapes.feature.domain.GridConstants
import shapes.feature.domain.ShapeDomainEntity

class ShapesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circlePaint: Paint = Paint().apply {
        color = BLUE
    }
    private val squarePaint: Paint = Paint().apply {
        color = GREEN
    }
    private val trianglePaint: Paint = Paint().apply {
        color = RED
        strokeWidth = 10f
    }
    private val path = Path()
    private val lastTouchDownXY = FloatArray(2)
    internal var clickListener: ClickListener? = null

    var shapes = emptyList<ShapeDomainEntity>()
        set(value) {
            field = value
            postInvalidate()
        }

    init {
        setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                lastTouchDownXY[0] = event.x
                lastTouchDownXY[1] = event.y
            }
            false
        }

        setOnClickListener {
            getClickedItem()?.let { clickListener?.onGridItemClick(it) }
        }

        setOnLongClickListener {
            getClickedItem()?.let {
                clickListener?.onGridItemLongClick(it)
            } ?: false
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.onEach { drawShape(it, canvas) }
    }

    private fun drawShape(shape: ShapeDomainEntity, canvas: Canvas) {
        when (shape.type) {

            ShapeDomainEntity.Type.SQUARE ->
                drawSquare(
                    canvas,
                    getCenterX(shape.id),
                    getCenterY(shape.id),
                    getRadius()
                )

            ShapeDomainEntity.Type.CIRCLE ->
                drawCircle(
                    canvas,
                    getCenterX(shape.id),
                    getCenterY(shape.id),
                    getRadius()
                )

            ShapeDomainEntity.Type.TRIANGLE ->
                drawTriangle(
                    canvas,
                    getCenterX(shape.id),
                    getCenterY(shape.id),
                    getRadius()
                )
        }
    }

    private fun getRadius(): Float {
        return min(width / GRID_SIZE, height / GRID_SIZE) * ITEM_TO_GRID_ITEM_RADIUS_RATIO
    }

    private fun getCenterX(gridPosition: Int): Float {
        val itemXPosition = (gridPosition - 1) % GRID_SIZE
        val itemWidth = width / GRID_SIZE

        return (itemXPosition * itemWidth) + (itemWidth * 0.5f)
    }

    private fun getCenterY(gridPosition: Int): Float {
        val itemYPosition = (gridPosition - 1) / GRID_SIZE
        val itemHeight = height / GRID_SIZE

        return (itemYPosition * itemHeight) + (itemHeight * 0.5f)
    }

    private fun drawCircle(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        canvas.drawCircle(cx, cy, radius, circlePaint)
    }

    private fun drawSquare(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        val left = cx - radius
        val top = cy - radius
        val right = cx + radius
        val bottom = cy + radius

        canvas.drawRect(left, top, right, bottom, squarePaint)
    }

    @Suppress("UnnecessaryVariable")
    private fun drawTriangle(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        // unnecessary variable for better naming
        val topX = cx
        val topY = cy - radius

        val leftX = cx - radius
        val leftY = cy + radius

        val rightX = cx + radius
        val rightY = cy + radius

        if (FILLED_TRIANGLE) {
            path.moveTo(topX, topY)
            path.lineTo(rightX, rightY)
            path.lineTo(leftX, leftY)
            path.lineTo(topX, topY)
            path.close()

            canvas.drawPath(path, trianglePaint)
        } else {
            canvas.drawLine(topX, topY, rightX, rightY, trianglePaint)
            canvas.drawLine(rightX, rightY, leftX, leftY, trianglePaint)
            canvas.drawLine(leftX, leftY, topX, topY, trianglePaint)
        }
    }

    private fun getClickedItem(): ShapeDomainEntity? {
        val gridWidth = width / GRID_SIZE
        val gridHeight = height / GRID_SIZE

        var clickedItemXPosition = 0
        var clickedItemYPosition = 0

        for (i in 1..GRID_SIZE) {
            if (lastTouchDownXY[0] > (i - 1) * gridWidth && lastTouchDownXY[0] < (i) * gridWidth) {
                clickedItemXPosition = i
            }

            if (lastTouchDownXY[1] > (i - 1) * gridHeight &&
                lastTouchDownXY[1] < (i) * gridHeight
            ) {
                clickedItemYPosition = i
            }
        }

        val position = clickedItemXPosition + ((clickedItemYPosition - 1) * GRID_SIZE)

        return shapes.firstOrNull { it.id == position }
    }

    interface ClickListener {
        fun onGridItemClick(shapeDomainEntity: ShapeDomainEntity)

        fun onGridItemLongClick(shapeDomainEntity: ShapeDomainEntity): Boolean
    }

    companion object {
        private const val GRID_SIZE = GridConstants.GRID_SIZE
        private const val ITEM_TO_GRID_ITEM_RADIUS_RATIO = 0.35f
        private const val FILLED_TRIANGLE = false
    }
}
