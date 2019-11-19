package app.akilesh.nex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.data.DeviceInfo
import app.akilesh.nex.viewholder.DeviceInfoViewHolder

class DeviceInfoAdapter(private val list: List<DeviceInfo>)
    : RecyclerView.Adapter<DeviceInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DeviceInfoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DeviceInfoViewHolder, position: Int) {
        val deviceInfo: DeviceInfo = list[position]
        holder.bind(deviceInfo)
    }

    override fun getItemCount(): Int = list.size

}