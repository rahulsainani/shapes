package shapes.feature.domain

import io.reactivex.Completable
import io.reactivex.Flowable

interface IShapesRepository {

    fun getAllShapes(): Flowable<List<ShapeDomainEntity>>

    fun addShape(shapeDomainEntity: ShapeDomainEntity): Completable

    fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable

    fun delete(shapeDomainEntity: ShapeDomainEntity): Completable

    fun deleteAllShapesByType(shapeType: ShapeDomainEntity.Type): Completable

    fun undo(): Completable
}