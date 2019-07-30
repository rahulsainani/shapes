package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject
import shapes.feature.domain.ShapeDomainEntity.Type.CIRCLE
import shapes.feature.domain.ShapeDomainEntity.Type.SQUARE
import shapes.feature.domain.ShapeDomainEntity.Type.TRIANGLE

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
