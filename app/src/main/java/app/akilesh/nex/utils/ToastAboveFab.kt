package app.akilesh.nex.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

class ToastAboveFab {
    fun show(context: Context?, text: String, length: Int ){
        val toast = Toast.makeText(context, text, length)
        toast.setGravity(Gravity.BOTTOM, 0, 230)
        toast.show()
    }
}

