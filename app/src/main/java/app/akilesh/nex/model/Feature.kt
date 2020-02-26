package app.akilesh.nex.model

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable

data class Feature (
        val featureName: String?,
        val buttonText: String,
        val buttonIcon: Drawable?,
        val color: ColorStateList?
)