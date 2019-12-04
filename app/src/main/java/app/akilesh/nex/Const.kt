package app.akilesh.nex

import app.akilesh.nex.Const.Url.platform
import app.akilesh.nex.Const.Url.release
import com.topjohnwu.superuser.Shell

object Const {

    object Url {
        val platform: MutableList<String> = Shell.sh("getprop ro.board.platform").exec().out
        val release: MutableList<String> = Shell.sh("getprop ro.build.version.release").exec().out

        const val githubReadMe = "https://github.com/Magisk-Modules-Repo/nokia-extensions/blob/master/README.md"
        const val xdaThread = "https://forum.xda-developers.com/nokia-7-plus/themes/magisk-module-nokia-extensions-android-t3865438?goto=newpost"
        const val telegramGroup = "https://t.me/NokiaExtensions"
        val githubRaw = "https://raw.githubusercontent.com/Akilesh-T/nokia-extensions-repo/master/${platform.component1()}/${release.component1()}/"
    }

    object Path {
        const val modulePath = "/data/adb/modules/nokia-extensions/"
        const val modulePrivAppsPath = modulePath + "system/priv-app/"
        const val firmwareVersionPath = "/proc/fver"
        const val skuidPath = "/dev/block/bootdevice/by-name/deviceinfo"
        const val gmsUpdatePrefPath = "/data/data/com.google.android.gms/shared_prefs/com.google.android.gms.update.storage.xml"
        const val OreoOverlay = "system/vendor/cust/overlay/600WW/"
        const val OreoOverlayTreble = "system/cust/overlay/600WW/"
        const val PieOverlay = "system/product/overlay/"
        var overlay = ""
        const val updateHistoryPath = "/data/misc/box/report/SWupgrade"
    }

    object Package {

        val appName = mapOf(
                "com.nbc.AIFloatingTouch.STouchActivity" to "AI Floating Touch",
                "com.evenwell.firewall.TrafficControl" to "App Traffic Control",
                "cust.settings.faceid.CustFacePlusSuggestionActivity" to "Face Unlock",
                "com.nbc.gameassistant.GameAssistantActivity" to "Game Assistant",
                "com.android.settings.Settings\$GestureSettingsActivity" to "Gesture overlay",
                "com.android.systemui.keyguard.glance.GlanceSettingsActivity" to "Glance Screen",
                "com.evenwell.cleaner.MainActivity" to "Junk Cleaner",
                "com.nbc.android.screenrecord.ui.ActivitySetting" to "Screen Recorder",
                "com.evenwell.smartboost.activities.MainActivity" to "Smart Boost",
                "com.evenwell.memorycleaner.MainActivity" to "Task Manager",
                "com.evenwell.viruscan.qscanner.VirusScannerActivity" to "Virus Scanner")

        val AIFloatingTouch = arrayListOf("com.nbc.AIFloatingTouch", "com.nbc.AIFloatingTouch.STouchActivity")

        val TrafficControl = arrayListOf("com.evenwell.firewall", "com.evenwell.firewall.TrafficControl")

        val FacePlusService = arrayListOf("com.android.settings", "com.android.settings.Settings\$SecurityDashboardActivity")

        val GameAssistant = arrayListOf("com.nbc.gameassistant", "com.nbc.gameassistant.GameAssistantActivity")

        val Gesture = arrayListOf("com.android.settings", "com.android.settings.Settings\$GestureSettingsActivity")

        val Glance = arrayListOf("com.android.systemui", "com.android.systemui.keyguard.glance.GlanceSettingsActivity")

        val JunkCleaner = arrayListOf("com.evenwell.cleaner", "com.evenwell.cleaner.MainActivity")

        val ScreenRecorder = arrayListOf("com.nbc.android.screenrecord", "com.nbc.android.screenrecord.ui.ActivitySetting")

        val SmartBoost = arrayListOf("com.evenwell.smartboost", "com.evenwell.smartboost.activities.MainActivity")

        val TaskManager = arrayListOf("com.evenwell.memorycleaner", "com.evenwell.memorycleaner.MainActivity")
    }

    object Feature {
         val featureDirs = mutableListOf(
                "AIFloatingTouch",
                "TrafficControl",
                "DataSpeedIndicator",
                "FacePlusService",
                "GameAssistant",
                "Glance",
                "HyperClip",
                "JunkCleaner",
                "OTAUpdate",
                "ScreenShotPlus",
                "ScreenRecorder",
                "SmartBoost",
                "TaskManager"
        )

        val featureNames = mutableMapOf(
                "AIFloatingTouch" to "AI Floating Touch",
                "TrafficControl" to "App Traffic Control",
                "DataSpeedIndicator" to "Data Speed Indicator",
                "FacePlusService" to "Face Unlock",
                "GameAssistant" to "Game Assistant",
                "Glance" to "Glance Screen",
                "HyperClip" to "Hyper Clip",
                "JunkCleaner" to "Junk Cleaner",
                "OTAUpdate" to "OTA Updater",
                "ScreenShotPlus" to "ScreenShot Plus",
                "ScreenRecorder" to "Screen Recorder",
                "SmartBoost" to "Smart Boost",
                "TaskManager" to "Task Manager"
        )

        val featureDirNames = featureNames.entries.associate { (k,v) -> v to k }

    }

    object File {

        val treble: MutableList<String> = Shell.sh("getprop ro.treble.enabled").exec().out
        init {
            if (release.component1() == "8.1.0") {
                if (treble.component1() != "true")
                    Path.overlay = Path.OreoOverlay
                if (treble.component1() == "true")
                    Path.overlay = Path.OreoOverlayTreble
            }
            if (release.component1() == "9")
                Path.overlay = Path.PieOverlay
        }

        fun addExtraFiles(){
            if (release.component1() == "9" && platform.component1() == "msm8937"){
                AIFloatingTouch.addAll(
                        listOf(
                                "system/priv-app/AIFloatingTouch/oat/arm64/AIFloatingTouch.odex",
                                "system/priv-app/AIFloatingTouch/oat/arm64/AIFloatingTouch.vdex"
                        )
                )

                TrafficControl.addAll(
                        listOf(
                                "system/priv-app/TrafficControl/oat/arm64/TrafficControl.odex",
                                "system/priv-app/TrafficControl/oat/arm64/TrafficControl.vdex"
                        )
                )

                HyperClip.addAll(
                        listOf(
                                "system/priv-app/HyperClip/oat/arm64/HyperClip.odex",
                                "system/priv-app/HyperClip/oat/arm64/HyperClip.vdex"
                        )
                )

                JunkCleaner.addAll(
                        listOf(
                                "system/priv-app/JunkCleaner/oat/arm/JunkCleaner.odex",
                                "system/priv-app/JunkCleaner/oat/arm/JunkCleaner.vdex",
                                "system/priv-app/JunkCleaner/oat/arm64/JunkCleaner.odex",
                                "system/priv-app/JunkCleaner/oat/arm64/JunkCleaner.vdex"
                        )
                )

                OTAUpdate.addAll(
                        listOf(
                                "system/priv-app/OTAUpdate/oat/arm64/OTAUpdate.odex",
                                "system/priv-app/OTAUpdate/oat/arm64/OTAUpdate.vdex"
                        )
                )

                ScreenShotPlus.addAll(
                        listOf(
                                "system/priv-app/ScreenShotPlus/oat/arm64/ScreenShotPlus.odex",
                                "system/priv-app/ScreenShotPlus/oat/arm64/ScreenShotPlus.vdex"
                        )
                )

                SmartBoost.addAll(
                        listOf(
                                "system/priv-app/SmartBoost/oat/arm64/SmartBoost.odex",
                                "system/priv-app/SmartBoost/oat/arm64/SmartBoost.vdex"
                        )
                )

                TaskManager.addAll(
                        listOf(
                                "system/priv-app/TaskManager/oat/arm64/TaskManager.odex",
                                "system/priv-app/TaskManager/oat/arm64/TaskManager.vdex"
                        )
                )
            }

            if (release.component1() != "8.1.0" && platform.component1() != "msm8937")
                TrafficControl.add(
                        Path.overlay + "com.evenwell.firewall.overlay.base.600WW.apk"
                )

            if (platform.component1() == "msm8937") {
                DataSpeedIndicator.addAll(
                        listOf(
                                "system/priv-app/DataSpeedIndicator/oat/arm64/DataSpeedIndicator.odex",
                                "system/priv-app/DataSpeedIndicator/oat/arm64/DataSpeedIndicator.vdex"
                        )
                )

                ScreenRecorder.addAll(
                        listOf(
                                "system/priv-app/ScreenRecorder/oat/arm64/ScreenRecorder.odex",
                                "system/priv-app/ScreenRecorder/oat/arm64/ScreenRecorder.vdex"
                        )
                )
            }

            if (release.component1() == "8.1.0")
                JunkCleaner.add("system/priv-app/JunkCleaner/lib/arm64/libdce-1.1.16-mfr.so")
            else if (release.component1() == "9")
                JunkCleaner.add("system/priv-app/JunkCleaner/lib/arm64/libdce-1.1.18-mfr.so")

        }

        private val AIFloatingTouch = mutableListOf(
                "system/priv-app/AIFloatingTouch/AIFloatingTouch.apk"
        )

        private val TrafficControl = mutableListOf(
                "system/priv-app/TrafficControl/TrafficControl.apk"
        )

        private val DataSpeedIndicator = mutableListOf(
                "system/priv-app/DataSpeedIndicator/DataSpeedIndicator.apk",
                Path.overlay + "com.evenwell.dataspeedindicator.overlay.base.600WW.apk"
        )

        private val FacePlusService = listOf(
                "system/priv-app/FacePlusService/lib/arm64/libFaceDetectCA.so",
                "system/priv-app/FacePlusService/lib/arm64/libMegviiUnlock-jni-1.2.so",
                "system/priv-app/FacePlusService/lib/arm64/libMegviiUnlock.so",
                "system/priv-app/FacePlusService/lib/arm64/libSNPE.so",
                "system/priv-app/FacePlusService/lib/arm64/libgnustl_shared.so",
                "system/priv-app/FacePlusService/lib/arm64/libmegface_meglive.so",
                "system/priv-app/FacePlusService/lib/arm64/libqspower-1.0.0.so",
                "system/priv-app/FacePlusService/lib/arm64/libsnpe_loader.so",
                "system/priv-app/FacePlusService/lib/arm64/libsymphony-cpu.so",
                "system/priv-app/FacePlusService/lib/arm64/libsymphonypower.so",
                "system/priv-app/FacePlusService/FacePlusService.apk"
        )

        private val GameAssistant = listOf(
                "system/priv-app/GameAssistant/GameAssistant.apk"
        )

        private val Glance = listOf(
                "system/priv-app/Glance/Glance.apk",
                "system/vendor/overlay/Glance_overlay.apk"
        )

        private val HyperClip = mutableListOf(
                "system/priv-app/HyperClip/HyperClip.apk",
                Path.overlay + "com.evenwell.hyperclip.overlay.base.600WW.apk"
        )

        private val JunkCleaner = mutableListOf(
                "system/priv-app/JunkCleaner/JunkCleaner.apk",
                Path.overlay + "com.evenwell.memorycleaner.overlay.base.600WW.apk",
                "system/priv-app/JunkCleaner/lib/arm64/libTmsdk-2.0.10-mfr.so"
        )

        private val OTAUpdate = mutableListOf(
                "system/priv-app/OTAUpdate/OTAUpdate.apk"
        )

        private val ScreenShotPlus = mutableListOf(
                "system/priv-app/ScreenShotPlus/ScreenShotPlus.apk",
                Path.overlay + "com.nbc.screenshotplus.overlay.base.600WW.apk"
        )

        private val ScreenRecorder = mutableListOf(
                "system/priv-app/ScreenRecorder/ScreenRecorder.apk",
                Path.overlay + "com.nbc.android.screenrecord.overlay.base.600WW.apk"
        )

        private val SmartBoost = mutableListOf(
                "system/priv-app/SmartBoost/SmartBoost.apk",
                "system/etc/permissions/SmartBoostFramework.xml",
                "system/etc/SmartBoostCfg",
                "system/framework/SmartBoostFramework.jar",
                "system/framework/oat/arm/SmartBoostFramework.odex",
                "system/framework/oat/arm64/SmartBoostFramework.odex",
                "system/framework/oat/arm/SmartBoostFramework.vdex",
                "system/framework/oat/arm64/SmartBoostFramework.vdex"
        )

        private val TaskManager = mutableListOf(
                "system/priv-app/TaskManager/TaskManager.apk"
        )

        val filesMap = mapOf(
                "AIFloatingTouch" to AIFloatingTouch,
                "TrafficControl" to TrafficControl,
                "DataSpeedIndicator" to DataSpeedIndicator,
                "FacePlusService" to FacePlusService,
                "GameAssistant" to GameAssistant,
                "Glance" to Glance,
                "HyperClip" to HyperClip,
                "JunkCleaner" to JunkCleaner,
                "OTAUpdate" to OTAUpdate,
                "ScreenShotPlus" to ScreenShotPlus,
                "ScreenRecorder" to ScreenRecorder,
                "SmartBoost" to SmartBoost,
                "TaskManager" to TaskManager
        )

    }

}
