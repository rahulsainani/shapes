package shapes.feature.data

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import shapes.base.data.ShapeDataStack
import shapes.base.rx.IOTransformer
import shapes.database.ShapesDao
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeDomainEntity

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper,
    private val shapeTypeDataMapper: ShapeTypeDataMapper,
    private val stack: ShapeDataStack
) : IShapesRepository {

    override fun getAllShapes(): Flowable<List<ShapeDomainEntity>> =
        shapesDao
            .getAllShapes()
            .compose(IOTransformer())
            .map(shapesListDomainMapper)

    override fun addShape(shapeDomainEntity: ShapeDomainEntity): Completable =
        addStateToStackAndThen {
            shapesDao.insert(shapeDataMapper.apply(shapeDomainEntity))
        }

    override fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable =
        addStateToStackAndThen {
            shapesDao.update(shapeDataMapper.apply(shapeDomainEntity))
        }

    override fun delete(shapeDomainEntity: ShapeDomainEntity): Completable =
        addStateToStackAndThen {
            shapesDao.delete(shapeDataMapper.apply(shapeDomainEntity))
        }

    override fun deleteAllShapesByType(shapeType: ShapeDomainEntity.Type): Completable =
        addStateToStackAndThen {
            shapesDao.deleteAllShapesByType(shapeTypeDataMapper.apply(shapeType))
        }

    override fun undo(): Completable =
        stack.pop()?.let {
            Completable
                .fromAction { shapesDao.deleteAndInsertInTransaction(it) }
                .compose(IOTransformer<Unit>())
        }
            ?: Completable.error(NoSuchElementException())

    private fun addStateToStackAndThen(func: () -> Completable): Completable =
        shapesDao
            .getAllShapesSingle()
            .doOnSuccess { stack.push(it) }
            .ignoreElement()
            .andThen(func())
            .compose(IOTransformer<Unit>())
}
