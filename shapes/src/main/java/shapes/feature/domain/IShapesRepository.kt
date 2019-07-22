package shapes.feature.domain

import io.reactivex.Completable
import io.reactivex.Flowable

interface IShapesRepository {

    fun getAllShapes(): Flowable<ShapeEntityList>

    fun addShape(shapeEntity: ShapeEntity): Completable

    fun deleteShape(shapeEntity: ShapeEntity): Completable
}