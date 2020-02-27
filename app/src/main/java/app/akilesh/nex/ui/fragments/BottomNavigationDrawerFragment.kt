package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    private fun showFragment(tag: String) {
        var fragment = fragmentManager!!.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = when (tag) {
                HelpFragment.TAG -> HelpFragment()

                UpdateFragment.TAG -> UpdateFragment()

                DeviceInfoFragment.TAG -> DeviceInfoFragment()

                AboutFragment.TAG -> AboutFragment()

                ManagerFragment.TAG -> ManagerFragment()

                else -> HomeFragment()
            }
        }

        fragmentManager!!
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, tag)
                .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.inflateMenu(R.menu.bottom_nav_drawer_menu)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_help -> {
                    showFragment(HelpFragment.TAG)
                    dismiss()
                }
                R.id.navigation_update -> {
                    showFragment(UpdateFragment.TAG)
                    dismiss()
                }
                R.id.navigation_device_info -> {
                    showFragment(DeviceInfoFragment.TAG)
                    dismiss()
                }
                R.id.navigation_about -> {
                    showFragment(AboutFragment.TAG)
                    dismiss()
                }
                R.id.navigation_manager -> {
                    showFragment(ManagerFragment.TAG)
                    dismiss()
                }
            }
            true
        }

    }

}