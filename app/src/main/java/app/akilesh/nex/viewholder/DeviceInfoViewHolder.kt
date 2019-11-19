package app.akilesh.nex.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.R
import app.akilesh.nex.data.DeviceInfo
import com.google.android.material.textview.MaterialTextView

class DeviceInfoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_device_info, parent, false)) {
    private var prop: MaterialTextView? = null
    private var value: MaterialTextView? = null


    init {
        prop = itemView.findViewById(R.id.prop)
        value = itemView.findViewById(R.id.value)
    }

    fun bind(deviceInfo: DeviceInfo) {
        prop?.text = deviceInfo.prop
        value?.text = deviceInfo.value
    }

}