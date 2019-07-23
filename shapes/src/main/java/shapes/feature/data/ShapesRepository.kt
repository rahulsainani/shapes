package shapes.feature.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import shapes.base.data.Stack
import shapes.base.database.ShapesDao
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.domain.ShapeDomainEntityList
import javax.inject.Inject

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper,
    private val stack: Stack
) : IShapesRepository {

    override fun getAllShapes(): Flowable<ShapeDomainEntityList> =
        shapesDao
            .getAllShapes()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map(shapesListDomainMapper)

    override fun addShape(shapeType: ShapeDomainEntity.Type, id: Int): Completable =
        addStateToStackAndThen { shapesDao.insert(shapeDataMapper.apply(shapeType, id)) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    override fun updateShape(shapeDomainEntity: ShapeDomainEntity): Completable =
        addStateToStackAndThen {
            shapesDao
                .update(shapeDataMapper.apply(shapeDomainEntity.type, shapeDomainEntity.id))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    override fun undo(): Completable =
        stack.pop()?.let {
            deleteAll()
                .andThen(shapesDao.insertAll(it))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
        }
            ?: Completable.error(NoSuchElementException())

    override fun deleteAll(): Completable =
        shapesDao
            .deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    private fun addStateToStackAndThen(func: () -> Completable): Completable =
        shapesDao
            .getAllShapesSingle()
            .doOnSuccess { stack.push(it) }
            .ignoreElement()
            .andThen(func.invoke())
}