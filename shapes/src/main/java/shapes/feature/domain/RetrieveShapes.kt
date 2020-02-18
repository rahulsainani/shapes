package shapes.feature.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RetrieveShapes @Inject constructor(
    private val repository: IShapesRepository
) {

    fun retrieveShapes(): Flow<List<ShapeDomainEntity>> = repository.getAllShapes()
}
