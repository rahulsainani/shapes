package shapes.feature.domain

import io.reactivex.Completable
import io.reactivex.Flowable

interface IShapesRepository {

    fun getAllShapes(): Flowable<ShapeDomainEntityList>

    fun addShape(shapeType: ShapeDomainEntity.Type, id: Int): Completable

    fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable

//    fun deleteShape(shapeEntity: ShapeEntity): Completable
}