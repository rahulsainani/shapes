package shapes.feature.presentation.stats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_stat.view.*
import shapes.feature.R

class StatisticsAdapter(
    private val listener: ClickListener
) : ListAdapter<StatisticsItemEntity, RecyclerView.ViewHolder>(
    StatisticsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_stat, parent, false)
        return StatItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val statItemViewHolder = holder as StatItemViewHolder
        statItemViewHolder.bind(getItem(position))
    }

    inner class StatItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(entity: StatisticsItemEntity) {
            with(itemView) {
                textShapeName.text = entity.shapeName
                textShapeCount.text = entity.count

                setOnClickListener { listener.onItemClick(entity) }
            }
        }
    }

    interface ClickListener {
        fun onItemClick(statisticsItemEntity: StatisticsItemEntity)
    }
}
