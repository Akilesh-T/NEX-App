package app.akilesh.nex.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.akilesh.nex.R
import app.akilesh.nex.data.Feature
import app.akilesh.nex.utils.ManagerUtil
import app.akilesh.nex.utils.ToastAboveFab
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class FeatureViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_features, parent, false)) {


    private var textView: MaterialTextView? = null
    private var button: MaterialButton? = null


    init {
        textView = itemView.findViewById(R.id.feature_name)
        button = itemView.findViewById(R.id.button)
    }

    fun bind(feature: Feature) {
        textView?.text = feature.featureName
        button?.text = feature.buttonText
        button?.icon = feature.buttonIcon
        button?.iconTint = feature.color
        button?.setTextColor(feature.color)
        button?.rippleColor = feature.color
        button?.strokeColor = feature.color
        button?.strokeWidth = 1
        button?.setOnClickListener {
           when(button?.text){

               "Remove" -> {
                   if(ManagerUtil().remove(textView?.text.toString()))
                       ToastAboveFab().show(itemView.context,"${textView?.text.toString()} removed. Reboot to apply changes.", 1 )

                   else
                       ToastAboveFab().show(itemView.context, "Error, ${textView?.text.toString()} couldn't be removed.", 1)
               }

               "Install" -> ManagerUtil().install(textView?.text.toString(), itemView.context)
           }
        }
    }

}