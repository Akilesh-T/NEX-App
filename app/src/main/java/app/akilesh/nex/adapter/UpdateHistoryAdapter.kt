package app.akilesh.nex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.data.UpdateHistory
import app.akilesh.nex.viewholder.UpdateHistoryViewHolder

class UpdateHistoryAdapter(private val list: List<UpdateHistory>)
    : RecyclerView.Adapter<UpdateHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UpdateHistoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UpdateHistoryViewHolder, position: Int) {
        val updateHistory = list[position]
        holder.bind(updateHistory)
    }

    override fun getItemCount(): Int = list.size

}