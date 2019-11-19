package app.akilesh.nex.utils

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.view.View
import androidx.fragment.app.FragmentActivity

class ShortcutUtil {

    private val mutableSet = mutableSetOf<ShortcutInfo>()

    fun createShortcut(activity: FragmentActivity?, view: View, packageName: String, className: String, shortLabel: String, longLabel: String, drawable: Int) {
        val shortcutManager = activity?.getSystemService(ShortcutManager::class.java)

        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName(packageName, className)

        val shortcut = ShortcutInfo.Builder(view.context, "pinned-shortcut-$shortLabel")
                .setShortLabel(shortLabel)
                .setLongLabel(longLabel)
                .setIcon(Icon.createWithResource(view.context, drawable))
                .setIntent(intent)
                .build()

        mutableSet.add(shortcut)
        shortcutManager!!.dynamicShortcuts = mutableSet.toMutableList()


        if (shortcutManager.isRequestPinShortcutSupported) {
            val pinShortcutInfo = ShortcutInfo.Builder(view.context, "pinned-shortcut-$shortLabel")
                    .build()
            val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo)

            val successCallback = PendingIntent.getBroadcast(view.context, 0,
                    pinnedShortcutCallbackIntent, 0)
            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.intentSender)
        }
    }
}