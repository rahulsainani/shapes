package shapes.feature.domain

import io.reactivex.Completable
import javax.inject.Inject

class AddShape @Inject constructor(
    private val repository: IShapesRepository
) {

    fun addShape(shapeEntity: ShapeEntity): Completable = repository.addShape(shapeEntity)
}