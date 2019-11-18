package shapes.feature.presentation.stats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_statistics.*
import shapes.base.di.AppComponentInjectHelper
import shapes.base.presentation.toGone
import shapes.base.presentation.toVisible
import shapes.feature.R
import shapes.feature.di.stats.DaggerStatisticsComponent

class StatisticsActivity : AppCompatActivity(), StatisticsAdapter.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: StatisticsViewModel

    private val adapter by lazy { StatisticsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        inject()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[StatisticsViewModel::class.java]

        recyclerStats.layoutManager = LinearLayoutManager(this)
        recyclerStats.adapter = adapter

        observeLiveData()
    }

    private fun inject() =
        DaggerStatisticsComponent.builder()
            .applicationComponent(AppComponentInjectHelper.provideAppComponent(applicationContext))
            .build()
            .inject(this)

    private fun observeLiveData() {
        viewModel.statsViewStateLiveData.observe(this, Observer { handleViewState(it) })
    }

    private fun handleViewState(viewState: StatisticsViewState) =
        when (viewState) {
            is StatisticsViewState.Empty -> displayEmptyState()
            is StatisticsViewState.Content -> displayStats(viewState.items)
        }

    private fun displayEmptyState() {
        recyclerStats.toGone()
        animationEmptyState.toVisible()
    }

    private fun displayStats(list: List<StatisticsItemEntity>) {
        animationEmptyState.toGone()
        adapter.submitList(list)
    }

    override fun onItemClick(statisticsItemEntity: StatisticsItemEntity) {
        viewModel.onItemClick(statisticsItemEntity.shapeType)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, StatisticsActivity::class.java)
    }
}
