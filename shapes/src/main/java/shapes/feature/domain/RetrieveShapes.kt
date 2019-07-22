package shapes.feature.domain

import io.reactivex.Flowable
import javax.inject.Inject

class RetrieveShapes @Inject constructor(
    private val repository: IShapesRepository
) {

    fun retrieveShapes(): Flowable<ShapeEntityList> = repository.getAllShapes()
}