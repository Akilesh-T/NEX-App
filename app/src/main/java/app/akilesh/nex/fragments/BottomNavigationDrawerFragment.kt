package app.akilesh.nex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.akilesh.nex.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    private lateinit var navigationView: NavigationView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
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
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView.inflateMenu(R.menu.bottom_nav_drawer_menu)
        navigationView.setNavigationItemSelectedListener { menuItem ->
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