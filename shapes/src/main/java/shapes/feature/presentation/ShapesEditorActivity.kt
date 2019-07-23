package shapes.feature.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import shapes.base.di.AppComponentInjectHelper
import shapes.feature.R
import shapes.feature.di.DaggerShapesComponent
import shapes.feature.di.ShapesModule
import shapes.feature.domain.ShapeDomainEntity
import javax.inject.Inject

class ShapesEditorActivity : AppCompatActivity(), ShapesView.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ShapesEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        viewModel.shapesLiveData.observe(this, Observer { handleScreenState(it) })
    }

    private fun handleScreenState(it: List<ShapeDomainEntity>) {
        shapesView.shapes = it
    }

    override fun onGridItemClick(shapeDomainEntity: ShapeDomainEntity) =
        viewModel.onShapeClick(shapeDomainEntity)
}
