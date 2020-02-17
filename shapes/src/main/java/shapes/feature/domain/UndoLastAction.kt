package shapes.feature.domain

import javax.inject.Inject

class UndoLastAction @Inject constructor(
    private val repository: IShapesRepository
) {

    suspend fun undo() = repository.undo()
}
