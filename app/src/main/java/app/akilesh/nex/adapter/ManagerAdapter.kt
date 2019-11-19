package app.akilesh.nex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.data.Feature
import app.akilesh.nex.viewholder.FeatureViewHolder

class ManagerAdapter(private val list: List<Feature>): RecyclerView.Adapter<FeatureViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeatureViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = list[position]
        holder.bind(feature)
    }

    override fun getItemCount(): Int = list.size

}