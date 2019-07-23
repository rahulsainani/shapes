package shapes.feature.domain

import io.reactivex.Completable
import shapes.feature.domain.ShapeDomainEntity.Type.*
import javax.inject.Inject

class SwitchShape @Inject constructor(
    private val repository: IShapesRepository
) {

    fun switchShape(shapeDomainEntity: ShapeDomainEntity): Completable {
        val updatedShape = when (shapeDomainEntity.type) {
            SQUARE -> CIRCLE
            CIRCLE -> TRIANGLE
            TRIANGLE -> SQUARE
        }

        return repository.updateShape(shapeDomainEntity.copy(type = updatedShape))
    }
}