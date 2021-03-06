package shapes.feature.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shapes.database.ShapesDao
import shapes.database.data.ShapeDataStack
import shapes.feature.domain.IShapesRepository
import shapes.feature.domain.ShapeDomainEntity

class ShapesRepository @Inject constructor(
    private val shapesDao: ShapesDao,
    private val shapesListDomainMapper: ShapesListDomainMapper,
    private val shapeDataMapper: ShapeDataMapper,
    private val shapeTypeDataMapper: ShapeTypeDataMapper,
    private val stack: ShapeDataStack
) : IShapesRepository {

    override fun getAllShapes(): Flow<List<ShapeDomainEntity>> =
        shapesDao
            .getAllShapes()
            .map { shapesListDomainMapper.apply(it) }

    override suspend fun getAllShapesOneShot(): List<ShapeDomainEntity> {
        val shapesDataList = shapesDao.getAllShapesOneShot()
        return shapesListDomainMapper.apply(shapesDataList)
    }

    override suspend fun addShape(shapeDomainEntity: ShapeDomainEntity) =
        addStateToStackAndThen {
            shapesDao.insert(shapeDataMapper.apply(shapeDomainEntity))
        }

    override suspend fun updateShape(shapeDomainEntity: ShapeDomainEntity) =
        addStateToStackAndThen {
            shapesDao.update(shapeDataMapper.apply(shapeDomainEntity))
        }

    override suspend fun delete(shapeDomainEntity: ShapeDomainEntity) =
        addStateToStackAndThen {
            shapesDao.delete(shapeDataMapper.apply(shapeDomainEntity))
        }

    override suspend fun deleteAllShapesByType(shapeType: ShapeDomainEntity.Type) =
        addStateToStackAndThen {
            shapesDao.deleteAllShapesByType(shapeTypeDataMapper.apply(shapeType))
        }

    override suspend fun undo() {
        stack.pop()?.let {
            shapesDao.deleteAndInsertInTransaction(it)
        } ?: throw NoSuchElementException("No more actions to undo")
    }

    private suspend fun addStateToStackAndThen(func: suspend () -> Unit) {
        val shapes = shapesDao.getAllShapesOneShot()
        stack.push(shapes)
        func()
    }
}
