package shapes.feature.presentation.stats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_statistics.*
import shapes.base.presentation.toGone
import shapes.base.presentation.toVisible
import shapes.feature.R

@AndroidEntryPoint
class StatisticsActivity : AppCompatActivity(), StatisticsAdapter.ClickListener {

    private val viewModel: StatisticsViewModel by viewModels()

    private val adapter by lazy { StatisticsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        recyclerStats.layoutManager = LinearLayoutManager(this)
        recyclerStats.adapter = adapter

        observeLiveData()
    }

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
