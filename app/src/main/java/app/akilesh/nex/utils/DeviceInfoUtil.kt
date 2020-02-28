package app.akilesh.nex.utils

import android.content.Context
import android.os.Build
import app.akilesh.nex.Const.Path.firmwareVersionPath
import app.akilesh.nex.Const.Path.skuidPath
import app.akilesh.nex.R
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.ShellUtils
import java.io.File

class DeviceInfoUtil {

    var model: String = ""
    var codeName: String = ""
    var buildFingerprint: String = ""
    var isAB: String = ""
    var activeSlot: String = ""
    var skuid: String = ""
    var buildVersion: String = ""
    var androidSecurityPatch: String = ""
    var vendorSecurityPatch: String = ""
    var isSAR: String = ""

    fun init(context: Context) {

        model = Build.MODEL
        codeName = Build.DEVICE
        buildFingerprint = Build.FINGERPRINT

        // Based on Magisk's implementation
        val shell = Shell.newInstance()
        val job = shell.newJob()
        job.add(context.resources.openRawResource(R.raw.props))
        job.add("get_props").exec()

        fun getVar(name: String) = ShellUtils.fastCmd(shell, "echo \$$name")

        activeSlot = getVar("SLOT")
        androidSecurityPatch = getVar("ASP")
        vendorSecurityPatch = getVar("VSP")
        buildVersion = getVar("BUILD")
        skuid = getVar("SKUID")
        isAB = if (getVar("AB").toBoolean()) "Yes" else "No"
        isSAR = getVar("SYSTEM_ROOT")

        if (shell.isRoot) {
            if (File(firmwareVersionPath).exists()) {
                buildVersion =
                    Shell.su("[ -f $firmwareVersionPath ] && head -n 1 $firmwareVersionPath").exec().out.component1().substring(4, 23)
            }
            skuid = Shell.su("strings $skuidPath | grep 600..").exec().out.component1()
        }
    }
}
