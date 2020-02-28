package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.akilesh.nex.Const.File.treble
import app.akilesh.nex.databinding.FragmentDeviceInfoBinding
import app.akilesh.nex.model.DeviceInfo
import app.akilesh.nex.ui.adapter.DeviceInfoAdapter
import app.akilesh.nex.utils.DeviceInfoUtil


class DeviceInfoFragment : Fragment(){

    private lateinit var binding: FragmentDeviceInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeviceInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val deviceInfoUtil = DeviceInfoUtil()
        deviceInfoUtil.init(requireContext())
        val info = mutableListOf(
            DeviceInfo("Model", deviceInfoUtil.model),
            DeviceInfo("Code Name", deviceInfoUtil.codeName),
            DeviceInfo("Build Version", deviceInfoUtil.buildVersion),
            DeviceInfo("Build Fingerprint", deviceInfoUtil.buildFingerprint),
            DeviceInfo("Skuid", deviceInfoUtil.skuid),
            DeviceInfo("Kernel", System.getProperty("os.version").toString()),
            DeviceInfo("Android Security Patch", deviceInfoUtil.androidSecurityPatch),
            DeviceInfo("SAR", deviceInfoUtil.isSAR)
        )

        if (treble == "true") {
            info.add(DeviceInfo("Treble", "Supported"))
        }
        else info.add(DeviceInfo("Treble", "Unsupported"))

        info.add(DeviceInfo("Seamless updates (A/B)", deviceInfoUtil.isAB))

        if (deviceInfoUtil.isAB == "Yes")
            info.add(DeviceInfo("Active Slot", deviceInfoUtil.activeSlot))

        if (deviceInfoUtil.vendorSecurityPatch.isNotBlank())
            info.add(DeviceInfo("Vendor Security Patch", deviceInfoUtil.vendorSecurityPatch))

        info.sortBy { it.prop }
        binding.infoRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DeviceInfoAdapter(info)
        }
    }
}
