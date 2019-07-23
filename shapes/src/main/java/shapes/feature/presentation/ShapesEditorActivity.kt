package shapes.feature.presentation

import android.os.Bundle
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

    private lateinit var viewmodel: ShapesEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inject()
        viewmodel =
            ViewModelProviders.of(this, viewModelFactory)[ShapesEditorViewModel::class.java]

        observeLiveData()

        buttonCircle.setOnClickListener { viewmodel.onCircleClick() }
        buttonSquare.setOnClickListener { viewmodel.onSquareClick() }
        buttonTriangle.setOnClickListener { viewmodel.onTriangleClick() }

        shapesView.clickListener = this
    }

    private fun inject() =
        DaggerShapesComponent.builder()
            .applicationComponent(AppComponentInjectHelper.provideAppComponent(applicationContext))
            .shapesModule(ShapesModule())
            .build()
            .inject(this)

    private fun observeLiveData() {
        viewmodel.shapesLiveData.observe(this, Observer { handleScreenState(it) })
    }

    private fun handleScreenState(it: List<ShapeDomainEntity>) {
        shapesView.shapes = it
    }

    override fun onGridItemClick(shapeDomainEntity: ShapeDomainEntity) =
        viewmodel.onShapeClick(shapeDomainEntity)
}
