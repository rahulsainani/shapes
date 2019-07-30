package shapes.feature.domain

import javax.inject.Inject
import shapes.feature.domain.GridConstants.GRID_SIZE

class RandomGridPositionGenerator @Inject constructor() {

    internal fun generate(list: List<Int>): Int =
        remainingGridPositions(list)
            .shuffled()
            .first()

    private fun remainingGridPositions(list: List<Int>) =
        (1..(GRID_SIZE * GRID_SIZE))
            .filter { list.contains(it).not() }
}
