package app.akilesh.nex.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.R
import app.akilesh.nex.model.UpdateHistory
import com.google.android.material.textview.MaterialTextView

class UpdateHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_update_history, parent, false)) {
    private var from: MaterialTextView? = null
    private var to: MaterialTextView? = null
    private var time: MaterialTextView? = null
    private var date: MaterialTextView? = null


    init {
        from = itemView.findViewById(R.id.body_from)
        to = itemView.findViewById(R.id.body_to)
        time = itemView.findViewById(R.id.body_time)
        date = itemView.findViewById(R.id.body_date)
    }

    fun bind(updateHistory: UpdateHistory) {
        from?.text = updateHistory.pre
        to?.text = updateHistory.post
        time?.text = updateHistory.time
        date?.text = updateHistory.date
    }

}