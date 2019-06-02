package app.akilesh.nex.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.akilesh.nex.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_bottomsheet.*

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    private fun showFragment(tag: String) {
        var fragment = fragmentManager!!.findFragmentByTag(tag)
        if (fragment == null) {
            when (tag) {
                HelpFragment.TAG -> {
                    fragment = HelpFragment()
                }
                DeviceFragment.TAG -> {
                    fragment = DeviceFragment()
                }
                AboutFragment.TAG -> {
                    fragment = AboutFragment()
                }
                else -> {
                    fragment = HomeFragment()
                }
            }
        }

        fragmentManager!!
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, tag)
                .commit()
    }

   override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_help -> {
                    showFragment(HelpFragment.TAG)
                    dismiss()
                }
                R.id.navigation_device_info -> {
                    showFragment(DeviceFragment.TAG)
                    dismiss()
                }
                R.id.navigation_about -> {
                    showFragment(AboutFragment.TAG)
                    dismiss()
                }
            }
            true
        }
    }

}