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
import app.akilesh.nex.Const.Package.GameAssistant
import app.akilesh.nex.Const.Package.Gesture
import app.akilesh.nex.Const.Package.Glance
import app.akilesh.nex.Const.Package.JunkCleaner
import app.akilesh.nex.Const.Package.ScreenRecorder
import app.akilesh.nex.Const.Package.SmartBoost
import app.akilesh.nex.Const.Package.TaskManager
import app.akilesh.nex.Const.Package.TrafficControl
import app.akilesh.nex.Const.Package.appName
import app.akilesh.nex.R
import app.akilesh.nex.databinding.FragmentHomeBinding
import app.akilesh.nex.utils.ShortcutUtil
import app.akilesh.nex.utils.ToastAboveFab


class HomeFragment : Fragment() {

    private val shortcutUtil = ShortcutUtil()
    private lateinit var binding: FragmentHomeBinding


    private fun isCallable(intent: Intent): Boolean {
        val activities = requireContext().packageManager.queryIntentActivities(intent,
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.aiDesc.setOnClickListener { launchApp(AIFloatingTouch[0], AIFloatingTouch[1]) }
        binding.fwDesc.setOnClickListener { launchApp(TrafficControl[0], TrafficControl[1]) }
        binding.faceUnlock.setOnClickListener { launchApp(FacePlusService[0], FacePlusService[1]) }
        binding.gaDesc.setOnClickListener { launchApp(GameAssistant[0], GameAssistant[1]) }
        binding.gesDesc.setOnClickListener { launchApp(Gesture[0], Gesture[1]) }
        binding.glDesc.setOnClickListener { launchApp(Glance[0], Glance[1]) }
        binding.jcDesc.setOnClickListener { launchApp(JunkCleaner[0], JunkCleaner[1]) }
        binding.srDesc.setOnClickListener { launchApp(ScreenRecorder[0], ScreenRecorder[1]) }
        binding.sbDesc.setOnClickListener { launchApp(SmartBoost[0], SmartBoost[1]) }
        binding.tmDesc.setOnClickListener { launchApp(TaskManager[0], TaskManager[1]) }

        val intent = Intent(Intent.ACTION_MAIN)

        binding.aiDesc.setOnLongClickListener {
            intent.setClassName(AIFloatingTouch[0], AIFloatingTouch[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    AIFloatingTouch[0], AIFloatingTouch[1],
                    appName[AIFloatingTouch[1]].toString(), appName[AIFloatingTouch[1]].toString(),
                    R.drawable.ic_ai)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[AIFloatingTouch[1]]} is not installed", 0)
                false
            }
        }

        binding.fwDesc.setOnLongClickListener {
            intent.setClassName(TrafficControl[0], TrafficControl[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    TrafficControl[0], TrafficControl[1],
                    appName[TrafficControl[1]].toString(), appName[TrafficControl[1]].toString(),
                    R.drawable.ic_data_usage)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[TrafficControl[1]]} is not installed", 0)
                false
            }
        }

        binding.faceUnlock.setOnLongClickListener {
            intent.setClassName(FacePlusService[0], FacePlusService[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    FacePlusService[0], FacePlusService[1],
                    appName[FacePlusService[1]].toString(), appName[FacePlusService[1]].toString(),
                    R.drawable.ic_face)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[FacePlusService[1]]} is not installed", 0)
                false
            }
        }

        binding.gaDesc.setOnLongClickListener {
            intent.setClassName(GameAssistant[0], GameAssistant[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    GameAssistant[0], GameAssistant[1],
                    appName[GameAssistant[1]].toString(), appName[GameAssistant[1]].toString(),
                    R.drawable.ic_game)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[GameAssistant[1]]} is not installed", 0)
                false
            }
        }

        binding.gesDesc.setOnLongClickListener {
            intent.setClassName(Gesture[0], Gesture[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    Gesture[0], Gesture[1],
                    appName[Gesture[1]].toString(), appName[Gesture[1]].toString(),
                    R.drawable.ic_gesture)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[Gesture[1]]} is not installed", 0)
                false
            }
        }

        binding.glDesc.setOnLongClickListener {
            intent.setClassName(Glance[0], Glance[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    Glance[0], Glance[1],
                    appName[Glance[1]].toString(), appName[Glance[1]].toString(),
                    R.drawable.ic_notifications)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[Glance[1]]} is not installed", 0)
                false
            }
        }

        binding.jcDesc.setOnLongClickListener {
            intent.setClassName(JunkCleaner[0], JunkCleaner[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    JunkCleaner[0], JunkCleaner[1],
                    appName[JunkCleaner[1]].toString(), appName[JunkCleaner[1]].toString(),
                    R.drawable.ic_delete_sweep)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[JunkCleaner[1]]} is not installed", 0)
                false
            }
        }

        binding.srDesc.setOnLongClickListener {
            intent.setClassName(ScreenRecorder[0], ScreenRecorder[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    ScreenRecorder[0], ScreenRecorder[1],
                    appName[ScreenRecorder[1]].toString(), appName[ScreenRecorder[1]].toString(),
                    R.drawable.ic_videocam)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[ScreenRecorder[1]]} is not installed", 0)
                false
            }
        }

        binding.sbDesc.setOnLongClickListener {
            intent.setClassName(SmartBoost[0], SmartBoost[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
                    SmartBoost[0], SmartBoost[1],
                    appName[SmartBoost[1]].toString(), appName[SmartBoost[1]].toString(),
                    R.drawable.ic_smart_boost)
                true
            } else {
                ToastAboveFab().show(activity, "${appName[SmartBoost[1]]} is not installed", 0)
                false
            }
        }

        binding.tmDesc.setOnLongClickListener {
            intent.setClassName(TaskManager[0], TaskManager[1])
            return@setOnLongClickListener if(isCallable(intent)){
                shortcutUtil.createShortcut(requireContext(),
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
}
