package shapes.feature.domain

import io.reactivex.Completable
import io.reactivex.Flowable

interface IShapesRepository {

    fun getAllShapes(): Flowable<List<ShapeDomainEntity>>

    fun addShape(shapeType: ShapeDomainEntity.Type, id: Int): Completable

    fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable

    fun delete(shapeDomainEntity: ShapeDomainEntity): Completable

    fun undo(): Completable

    fun deleteAll(): Completable
}