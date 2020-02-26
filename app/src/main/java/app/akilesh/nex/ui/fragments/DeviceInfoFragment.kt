package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.akilesh.nex.Const.File.treble
import app.akilesh.nex.R
import app.akilesh.nex.ui.adapter.DeviceInfoAdapter
import app.akilesh.nex.model.DeviceInfo
import app.akilesh.nex.utils.DeviceInfoUtil
import kotlinx.android.synthetic.main.fragment_device_info.*


class DeviceInfoFragment : Fragment(){

    private var info = mutableListOf<DeviceInfo>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val deviceInfoUtil = DeviceInfoUtil()
        deviceInfoUtil.init()
        info = mutableListOf(
                DeviceInfo("Model", deviceInfoUtil.model),
                DeviceInfo("Code Name", deviceInfoUtil.codeName),
                DeviceInfo("Build Version", deviceInfoUtil.buildVersion),
                DeviceInfo("Build Fingerprint", deviceInfoUtil.buildFingerprint),
                DeviceInfo("Active Slot", deviceInfoUtil.activeSlot),
                DeviceInfo("Skuid", deviceInfoUtil.skuid),
                DeviceInfo("Kernel", System.getProperty("os.version").toString()),
                DeviceInfo("Android Security Patch", deviceInfoUtil.androidSecurityPatch)
        )

        if (treble.component1() == "true")
            info.add(DeviceInfo("Vendor Security Patch", deviceInfoUtil.vendorSecurityPatch))

        info_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DeviceInfoAdapter(info)
        }

    }

    companion object {
        internal const val TAG = "DeviceInfoFragment"
    }

}
