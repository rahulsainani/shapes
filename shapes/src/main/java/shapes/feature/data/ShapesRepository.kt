package shapes.feature.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import shapes.base.database.ShapesDao
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.ShapeDomainEntityList
import javax.inject.Inject

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper
) : IShapesRepository {

    override fun getAllShapes(): Flowable<ShapeDomainEntityList> =
        shapesDao
            .getAllShapes()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map(shapesListDomainMapper)

    override fun addShape(shapeType: ShapeDomainEntity.Type, id: Int): Completable =
        shapesDao
            .insert(shapeDataMapper.apply(shapeType, id))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    override fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable =
        shapesDao
            .update(shapeDataMapper.apply(shapeDomainEntity.type, shapeDomainEntity.id))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

//    override fun deleteShape(shapeEntity: ShapeEntity): Completable =
//        shapesDao
//            .delete(shapeDataMapper.apply(shapeEntity))
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.computation())
}