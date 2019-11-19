package app.akilesh.nex.utils

import android.os.Build
import app.akilesh.nex.Const.Path.firmwareVersionPath
import app.akilesh.nex.Const.Path.skuidPath
import com.topjohnwu.superuser.Shell
import java.io.File

class DeviceInfoUtil {

    var model: String = ""
    var codeName: String = ""
    var buildFingerprint: String = ""
    var activeSlot: String = ""
    var skuid: String = ""
    var buildVersion: String = ""
    var androidSecurityPatch: String = ""
    var vendorSecurityPatch: String = ""

    fun init(){

        model = Build.MODEL
        codeName = Build.DEVICE
        buildFingerprint = Build.FINGERPRINT

        var file  = false
        if(File(firmwareVersionPath).exists())
            file = true

        val outputs = mutableListOf<String>()
        Shell.sh("getprop ro.build.ab_update").to(outputs).exec()
        activeSlot = if(outputs.component1() == "true") {
            val out = Shell.sh("getprop ro.boot.slot_suffix").exec().out
            out.toString().drop(2).dropLast(1)
        }
        else "A only"

        buildVersion = if(Shell.rootAccess() && file) {
            Shell.su("[ -f $firmwareVersionPath ] && head -n 1 $firmwareVersionPath").to(outputs).exec()
            outputs.component2().substring(4, 23)
        }
        else {
            Shell.sh("getprop ro.build.version.incremental").to(outputs).exec()
            outputs.component2()
        }

        skuid = if(Shell.rootAccess()) {
            Shell.su("strings $skuidPath | grep 600..").to(outputs).exec()
            outputs.component3()
        }
        else {
            Shell.sh("getprop ro.cda.skuid.id").to(outputs).exec()
            outputs.component3()
        }

        Shell.sh("getprop ro.build.version.security_patch").to(outputs).exec()
        androidSecurityPatch = outputs.component4()

        Shell.sh("getprop ro.vendor.build.security_patch").to(outputs).exec()
        vendorSecurityPatch = outputs.component5()

    }

}