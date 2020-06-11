package shapes.feature.presentation.editor

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shapes_editor.*
import shapes.base.presentation.toGone
import shapes.base.presentation.toVisible
import shapes.feature.R
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.presentation.stats.StatisticsActivity

@AndroidEntryPoint
class ShapesEditorActivity : AppCompatActivity(), ShapesView.ClickListener {

    private val viewModel: ShapesEditorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shapes_editor)

        observeLiveData()

        buttonCircle.setOnClickListener { viewModel.onCircleClick() }
        buttonSquare.setOnClickListener { viewModel.onSquareClick() }
        buttonTriangle.setOnClickListener { viewModel.onTriangleClick() }

        shapesView.clickListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.undo -> viewModel.onUndoClick()
            R.id.statistics -> startActivity(StatisticsActivity.createIntent(this))
        }
        return true
    }

    private fun observeLiveData() {
        viewModel.viewStateLiveData.observe(this, Observer { handleViewState(it) })
    }

    private fun handleViewState(viewState: ShapesEditorViewState) =
        when (viewState) {
            is ShapesEditorViewState.Empty -> displayEmptyState()
            is ShapesEditorViewState.Content -> displayShapes(viewState.items)
        }

    private fun displayEmptyState() {
        textEducation.toVisible()
        shapesView.shapes = emptyList()
    }

    private fun displayShapes(shapesList: List<ShapeDomainEntity>) {
        textEducation.toGone()
        shapesView.shapes = shapesList
    }

    override fun onGridItemClick(shapeDomainEntity: ShapeDomainEntity) =
        viewModel.onShapeClick(shapeDomainEntity)

    override fun onGridItemLongClick(shapeDomainEntity: ShapeDomainEntity): Boolean {
        viewModel.onShapeLongClick(shapeDomainEntity)
        return true
    }
}
