package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.akilesh.nex.Const.Anim.navAnim
import app.akilesh.nex.Const.Url.platform
import app.akilesh.nex.Const.Url.release
import app.akilesh.nex.R
import app.akilesh.nex.databinding.FragmentBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.inflateMenu(R.menu.bottom_nav_drawer_menu)
        if ( (platform == "msm8998" && release == "8.1.0")
            || (platform != "msm8998" && platform != "msm8937" && platform != "sdm660") ) {
            binding.navigationView.menu.findItem(R.id.navigation_manager).isVisible = false
        }
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_help -> {
                    findNavController().navigate(R.id.helpFragment, null, navAnim)
                    dismiss()
                }
                R.id.navigation_update -> {
                    findNavController().navigate(R.id.updateFragment, null, navAnim)
                    dismiss()
                }
                R.id.navigation_device_info -> {
                    findNavController().navigate(R.id.deviceInfoFragment, null, navAnim)
                    dismiss()
                }
                R.id.navigation_about -> {
                    findNavController().navigate(R.id.aboutFragment, null, navAnim)
                    dismiss()
                }
                R.id.navigation_manager -> {
                    findNavController().navigate(R.id.managerFragment, null, navAnim)
                    dismiss()
                }
            }
            true
        }
    }
}
