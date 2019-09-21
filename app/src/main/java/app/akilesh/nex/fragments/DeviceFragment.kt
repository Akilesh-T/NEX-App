package app.akilesh.nex.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import com.topjohnwu.superuser.Shell
import kotlinx.android.synthetic.main.fragment_device.*
import java.io.File


class DeviceFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        brand.text = Build.BRAND
        model.text = Build.MODEL
        codeName.text = Build.DEVICE
        buildFingerprint.text = Build.FINGERPRINT
        val firmwareVersionPath = "/proc/fver"
        val skuidPath = "/proc/cda/skuid"
        var files  = false
        if(File(firmwareVersionPath).exists() && File(skuidPath).exists())
           files = true

        val outputs = mutableListOf<String>()
        Shell.sh("getprop ro.build.ab_update").to(outputs).exec()
        if(outputs.component1() == "true") {
            val out = Shell.sh("getprop ro.boot.slot_suffix").exec().out
            slot.text = out.toString().drop(2).dropLast(1)
        }

        if(Shell.rootAccess() && files) {
            Shell.su("[ -f $firmwareVersionPath ] && head -n 1 $firmwareVersionPath").to(outputs).exec()
            buildVersion.text = outputs.component2().substring(4, 23)

            Shell.su("[ -f $skuidPath ] && head -n 1 $skuidPath").to(outputs).exec()
            skuid.text = outputs.component3()
        }
        else {
            Shell.sh("getprop ro.build.version.incremental").to(outputs).exec()
            buildVersion.text = outputs.component2()

            Shell.sh("getprop ro.cda.skuid.id").to(outputs).exec()
            skuid.text = outputs.component3()
        }

        Shell.sh("getprop ro.build.version.security_patch").to(outputs).exec()
        asp.text = outputs.component4()

        Shell.sh("getprop ro.vendor.build.security_patch").to(outputs).exec()
        vsp.text = outputs.component5()



    }
    companion object {
        internal const val TAG = "DeviceFragmentTag"
    }

}
