package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_help -> {
                    findNavController().navigate(R.id.helpFragment)
                    dismiss()
                }
                R.id.navigation_update -> {
                    findNavController().navigate(R.id.updateFragment)
                    dismiss()
                }
                R.id.navigation_device_info -> {
                    findNavController().navigate(R.id.deviceInfoFragment)
                    dismiss()
                }
                R.id.navigation_about -> {
                    findNavController().navigate(R.id.aboutFragment)
                    dismiss()
                }
                R.id.navigation_manager -> {
                    findNavController().navigate(R.id.managerFragment)
                    dismiss()
                }
            }
            true
        }
    }
}
