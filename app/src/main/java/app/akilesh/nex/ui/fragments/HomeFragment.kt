package app.akilesh.nex.ui.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.Const.Package.AIFloatingTouch
import app.akilesh.nex.Const.Package.FacePlusService
import app.akilesh.nex.Const.Package.TrafficControl
import app.akilesh.nex.Const.Package.GameAssistant
import app.akilesh.nex.Const.Package.Gesture
import app.akilesh.nex.Const.Package.Glance
import app.akilesh.nex.Const.Package.JunkCleaner
import app.akilesh.nex.Const.Package.ScreenRecorder
import app.akilesh.nex.Const.Package.SmartBoost
import app.akilesh.nex.Const.Package.TaskManager
import app.akilesh.nex.Const.Package.appName
import app.akilesh.nex.R
import app.akilesh.nex.utils.ShortcutUtil
import app.akilesh.nex.utils.ToastAboveFab
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),  View.OnClickListener, View.OnLongClickListener {

    private val shortcutUtil = ShortcutUtil()


    private fun isCallable(intent: Intent): Boolean {
        val activities = context!!.packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY)
        return activities.isNotEmpty()
    }

    private fun launchApp(packageName: String, className: String){

        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName(packageName, className)
        if (isCallable(intent))
            startActivity(intent)
        else {
           ToastAboveFab().show(activity, "${appName[className]} is not installed", 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ai_desc.setOnClickListener(this)
        fw_desc.setOnClickListener(this)
        face_unlock.setOnClickListener(this)
        ga_desc.setOnClickListener(this)
        ges_desc.setOnClickListener(this)
        gl_desc.setOnClickListener(this)
        jc_desc.setOnClickListener(this)
        sr_desc.setOnClickListener(this)
        sb_desc.setOnClickListener(this)
        tm_desc.setOnClickListener(this)

        ai_desc.setOnLongClickListener(this)
        fw_desc.setOnLongClickListener(this)
        face_unlock.setOnLongClickListener(this)
        ga_desc.setOnLongClickListener(this)
        ges_desc.setOnLongClickListener(this)
        gl_desc.setOnLongClickListener(this)
        jc_desc.setOnLongClickListener(this)
        sr_desc.setOnLongClickListener(this)
        sb_desc.setOnLongClickListener(this)
        tm_desc.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.ai_desc -> launchApp(AIFloatingTouch[0], AIFloatingTouch[1])

            R.id.fw_desc -> launchApp(TrafficControl[0], TrafficControl[1])

            R.id.face_unlock -> launchApp(FacePlusService[0], FacePlusService[1])

            R.id.ga_desc -> launchApp(GameAssistant[0], GameAssistant[1])

            R.id.ges_desc -> launchApp(Gesture[0], Gesture[1])

            R.id.gl_desc -> launchApp(Glance[0], Glance[1])

            R.id.jc_desc -> launchApp(JunkCleaner[0], JunkCleaner[1])

            R.id.sr_desc -> launchApp(ScreenRecorder[0], ScreenRecorder[1])

            R.id.sb_desc -> launchApp(SmartBoost[0], SmartBoost[1])

            R.id.tm_desc -> launchApp(TaskManager[0], TaskManager[1])

        }
    }

    override fun onLongClick(v: View?): Boolean {

        val intent = Intent(Intent.ACTION_MAIN)
        when(v?.id){
            R.id.ai_desc -> {
                intent.setClassName(AIFloatingTouch[0], AIFloatingTouch[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            AIFloatingTouch[0], AIFloatingTouch[1],
                            appName[AIFloatingTouch[1]].toString(), appName[AIFloatingTouch[1]].toString(),
                            R.drawable.ic_ai)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[AIFloatingTouch[1]]} is not installed", 0)
                    false
                }
            }


            R.id.fw_desc -> {
                intent.setClassName(TrafficControl[0], TrafficControl[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            TrafficControl[0], TrafficControl[1],
                            appName[TrafficControl[1]].toString(), appName[TrafficControl[1]].toString(),
                            R.drawable.ic_data_usage)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[TrafficControl[1]]} is not installed", 0)
                    false
                }
            }

            R.id.face_unlock -> {
                intent.setClassName(FacePlusService[0], FacePlusService[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            FacePlusService[0], FacePlusService[1],
                            appName[FacePlusService[1]].toString(), appName[FacePlusService[1]].toString(),
                            R.drawable.ic_face)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[FacePlusService[1]]} is not installed", 0)
                    false
                }
            }

            R.id.ga_desc -> {
                intent.setClassName(GameAssistant[0], GameAssistant[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            GameAssistant[0], GameAssistant[1],
                            appName[GameAssistant[1]].toString(), appName[GameAssistant[1]].toString(),
                            R.drawable.ic_game)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[GameAssistant[1]]} is not installed", 0)
                    false
                }
            }

            R.id.ges_desc -> {
                intent.setClassName(Gesture[0], Gesture[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            Gesture[0], Gesture[1],
                            appName[Gesture[1]].toString(), appName[Gesture[1]].toString(),
                            R.drawable.ic_gesture)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[Gesture[1]]} is not installed", 0)
                    false
                }
            }

            R.id.gl_desc -> {
                intent.setClassName(Glance[0], Glance[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            Glance[0], Glance[1],
                            appName[Glance[1]].toString(), appName[Glance[1]].toString(),
                            R.drawable.ic_notifications)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[Glance[1]]} is not installed", 0)
                    false
                }
            }

            R.id.jc_desc -> {
                intent.setClassName(JunkCleaner[0], JunkCleaner[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            JunkCleaner[0], JunkCleaner[1],
                            appName[JunkCleaner[1]].toString(), appName[JunkCleaner[1]].toString(),
                            R.drawable.ic_delete_sweep)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[JunkCleaner[1]]} is not installed", 0)
                    false
                }
            }

            R.id.sr_desc -> {
                intent.setClassName(ScreenRecorder[0], ScreenRecorder[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            ScreenRecorder[0], ScreenRecorder[1],
                            appName[ScreenRecorder[1]].toString(), appName[ScreenRecorder[1]].toString(),
                            R.drawable.ic_videocam)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[ScreenRecorder[1]]} is not installed", 0)
                    false
                }
            }

            R.id.sb_desc -> {
                intent.setClassName(SmartBoost[0], SmartBoost[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            SmartBoost[0], SmartBoost[1],
                            appName[SmartBoost[1]].toString(), appName[SmartBoost[1]].toString(),
                            R.drawable.ic_smart_boost)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[SmartBoost[1]]} is not installed", 0)
                    false
                }
            }

            R.id.tm_desc -> {
                intent.setClassName(TaskManager[0], TaskManager[1])
                return if(isCallable(intent)){
                    shortcutUtil.createShortcut(activity, v,
                            TaskManager[0], TaskManager[1],
                            appName[TaskManager[1]].toString(), appName[TaskManager[1]].toString(),
                            R.drawable.ic_tm)
                    true
                } else {
                    ToastAboveFab().show(activity, "${appName[TaskManager[1]]} is not installed", 0)
                    false
                }
            }

        }

        return false
    }

    companion object {

        internal const val TAG = "HomeFragment"

    }

}
