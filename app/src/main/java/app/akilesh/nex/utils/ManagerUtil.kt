package app.akilesh.nex.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import app.akilesh.nex.Const.Feature.featureDirNames
import app.akilesh.nex.Const.Feature.featureDirs
import app.akilesh.nex.Const.Feature.featureNames
import app.akilesh.nex.Const.File.filesMap
import app.akilesh.nex.Const.Path.modulePath
import app.akilesh.nex.Const.Path.modulePrivAppsPath
import app.akilesh.nex.Const.Url.githubRaw
import app.akilesh.nex.Const.Url.platform
import app.akilesh.nex.Const.Url.release
import app.akilesh.nex.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import com.topjohnwu.superuser.Shell
import java.io.File
import java.math.RoundingMode

class ManagerUtil {

    val installed = mutableListOf<String>()
    val notInstalled = mutableListOf<String>()

    fun initLists(){
        if (release != "9") {
            featureDirs.remove("GameAssistant")
            featureNames.remove("GameAssistant", "Game Assistant")
        }
        if (platform == "sdm660") {
            featureDirs.remove("Glance")
            featureNames.remove("Glance", "Glance Screen")
        }
        if (platform != "sdm660") {
            featureDirs.remove("FacePlusService")
            featureNames.remove("FacePlusService", "Face Unlock")
        }

        featureDirs.forEach {
            val path = modulePrivAppsPath + it
            val outputs = mutableListOf<String>()
            Shell.su("[ -d $path ] && echo y").to(outputs).exec()
            if(outputs.isNotEmpty() && outputs.component1().isNotBlank())
                installed.add(it)
            else
                notInstalled.add(it)
            outputs.clear()
        }
    }

    fun remove(feature: String): Boolean {
        val op = mutableListOf<String>()
        val dir = featureDirNames[feature].toString()
        filesMap[dir]?.forEach { filePath ->
            Shell.su("rm -rf $modulePath$filePath").to(op).exec()
        }
        Shell.su("rm -rf $modulePrivAppsPath$dir").to(op).exec()
        return if(op.isNullOrEmpty()) {
            Log.d("Remove", "$feature was removed")
            true
        }
        else
            false
    }

    fun install(feature: String, context: Context) {
        val dir = featureDirNames[feature].toString()
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("download", "Downloads", importance).apply {
            description = "Download notification"
        }
        val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, "download")
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setContentTitle("Downloading files for $feature")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true)
                .setOnlyAlertOnce(true)

        notificationManager.notify(1, builder.build())
        val toMB = 1024.0 * 1024.0

        filesMap[dir]?.forEach { filePath ->

            val file = filePath.substring(filePath.lastIndexOf("/")+1)
            val directory = filePath.substring(0, filePath.lastIndexOf("/"))
            Fuel.download(githubRaw + filePath)
                    .fileDestination { _, _ ->
                        File(context.filesDir, file)
                    }
                    .progress { readBytes, totalBytes ->

                        val remaining = readBytes.div(toMB).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                        val total = totalBytes.div(toMB).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                        val progress = "${remaining}MB / ${total}MB"
                        builder.setContentText(progress)
                                .setProgress(totalBytes.toInt(), readBytes.toInt(), false)
                        notificationManager.notify(1, builder.build())
                    }
                    .responseString { _, _, result ->
                        result.failure {
                            Shell.su("rm -f ${context.filesDir}/$file").exec()
                            builder.setContentTitle("$feature couldn't be downloaded")
                                    .setContentText(it.message)
                                    .setSmallIcon(android.R.drawable.stat_notify_error)
                                    .setStyle(NotificationCompat.BigTextStyle().bigText(it.message))
                                    .setOngoing(false)
                            notificationManager.notify(1, builder.build())
                            Log.d("Download error", it.toString())
                        }
                        result.success {
                            builder.setContentTitle("$feature downloaded")
                                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                            notificationManager.notify(1, builder.build())
                            Log.d("Download success", "$file download complete")

                            val mk = Shell.su("mkdir -p $modulePath$directory").exec().out
                            if (mk.isNullOrEmpty()) {
                                val cp = Shell.su("cp -f ${context.filesDir}/$file $modulePath$filePath").exec().out
                                if(cp.isNullOrEmpty()) {
                                    if (filesMap[dir]?.indexOf(filePath) == filesMap[dir]?.lastIndex) {
                                        val intent = Intent(context, NotificationRebootActionService::class.java)
                                                .setAction("reboot")
                                        val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

                                        builder.setOngoing(false)
                                                .setContentTitle("$feature installed")
                                                .setContentText("Reboot to apply changes")
                                                .setSmallIcon(R.drawable.ic_tm)
                                                .addAction(R.drawable.ic_tm, "Reboot", pendingIntent)
                                        notificationManager.notify(1, builder.build())
                                        Shell.su("rm -f ${context.filesDir}/$file").exec()
                                    }
                                }
                            }
                        }
                    }
            Shell.su("rm -f ${context.filesDir}/$file").exec()
        }
    }

}