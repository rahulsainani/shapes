package shapes.feature.data

import io.reactivex.Completable
import io.reactivex.Flowable
import shapes.base.data.Stack
import shapes.base.database.ShapesDao
import shapes.base.rx.CompletableNetworkTransformer
import shapes.base.rx.FlowableNetworkTransformer
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper,
    private val shapeTypeDataMapper: ShapeTypeDataMapper,
    private val stack: Stack
) : IShapesRepository {

    override fun getAllShapes(): Flowable<List<ShapeDomainEntity>> =
        shapesDao
            .getAllShapes()
            .compose(FlowableNetworkTransformer())
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
            shapesDao
                .deleteAll()
                .andThen(shapesDao.insertAll(it))
                .compose(CompletableNetworkTransformer())
        }
            ?: Completable.error(NoSuchElementException())

    private fun addStateToStackAndThen(func: () -> Completable): Completable =
        shapesDao
            .getAllShapesSingle()
            .doOnSuccess { stack.push(it) }
            .ignoreElement()
            .andThen(func())
            .compose(CompletableNetworkTransformer())
}