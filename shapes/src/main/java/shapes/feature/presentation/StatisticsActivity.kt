package shapes.feature.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_statistics.*
import shapes.base.di.AppComponentInjectHelper
import shapes.feature.R
import shapes.feature.di.DaggerStatisticsComponent
import shapes.feature.di.StatisticsModule
import javax.inject.Inject

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

        articles_recyclerview.layoutManager = LinearLayoutManager(this)
        articles_recyclerview.adapter = adapter

        observeLiveData()
    }

    private fun inject() =
        DaggerStatisticsComponent.builder()
            .applicationComponent(AppComponentInjectHelper.provideAppComponent(applicationContext))
            .statisticsModule(StatisticsModule())
            .build()
            .inject(this)

    private fun observeLiveData() {
        viewModel.statsListLiveData.observe(this, Observer { handleScreenState(it) })
    }

    private fun handleScreenState(list: List<StatisticsItemEntity>) =
        adapter.submitList(list)

    override fun onItemClick(article: StatisticsItemEntity) {
        //TODO delete all shapes of this category
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, StatisticsActivity::class.java)
    }
}