package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject

class DeleteShape @Inject constructor(
    private val repository: IShapesRepository
) {

    fun delete(shapeDomainEntity: ShapeDomainEntity): Completable =
        repository.delete(shapeDomainEntity)
}
