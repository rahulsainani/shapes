package shapes.feature.domain

import javax.inject.Inject

class DeleteShape @Inject constructor(
    private val repository: IShapesRepository
) {

    suspend fun delete(shapeDomainEntity: ShapeDomainEntity) =
        repository.delete(shapeDomainEntity)
}
