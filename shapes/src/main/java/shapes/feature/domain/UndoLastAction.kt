package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject

class UndoLastAction @Inject constructor(
    private val repository: IShapesRepository
) {

    fun undo(): Completable = repository.undo()
}
