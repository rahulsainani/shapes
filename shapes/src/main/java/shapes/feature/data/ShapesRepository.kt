package shapes.feature.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import shapes.base.database.ShapesDao
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeEntity
import shapes.feature.domain.ShapeEntityList
import javax.inject.Inject

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper
) : IShapesRepository {

    override fun getAllShapes(): Flowable<ShapeEntityList> =
        shapesDao
            .getAllShapes()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map(shapesListDomainMapper)

    override fun addShape(shapeEntity: ShapeEntity): Completable =
        shapesDao
            .insert(shapeDataMapper.apply(shapeEntity))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    override fun deleteShape(shapeEntity: ShapeEntity): Completable =
        shapesDao
            .delete(shapeDataMapper.apply(shapeEntity))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}