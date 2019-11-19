package app.akilesh.nex.utils

import android.app.IntentService
import android.content.Intent
import com.topjohnwu.superuser.Shell

class NotificationRebootActionService: IntentService("debug") {

    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        if(action == "reboot")
            Shell.su("/system/bin/svc power reboot || /system/bin/reboot").submit()
    }
}