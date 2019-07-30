package shapes.feature.presentation.editor

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_shapes_editor.*
import shapes.base.di.AppComponentInjectHelper
import shapes.feature.R
import shapes.feature.di.editor.DaggerShapesComponent
import shapes.feature.di.editor.ShapesModule
import shapes.feature.domain.ShapeDomainEntity
import shapes.feature.presentation.stats.StatisticsActivity

class ShapesEditorActivity : AppCompatActivity(),
    ShapesView.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ShapesEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shapes_editor)

        inject()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[ShapesEditorViewModel::class.java]

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

    private fun inject() =
        DaggerShapesComponent.builder()
            .applicationComponent(AppComponentInjectHelper.provideAppComponent(applicationContext))
            .shapesModule(ShapesModule())
            .build()
            .inject(this)

    private fun observeLiveData() {
        viewModel.shapesLiveData.observe(this, Observer { displayShapes(it) })
    }

    private fun displayShapes(shapesList: List<ShapeDomainEntity>) {
        shapesView.shapes = shapesList
    }

    override fun onGridItemClick(shapeDomainEntity: ShapeDomainEntity) =
        viewModel.onShapeClick(shapeDomainEntity)

    override fun onGridItemLongClick(shapeDomainEntity: ShapeDomainEntity): Boolean {
        viewModel.onShapeLongClick(shapeDomainEntity)
        return true
    }
}
