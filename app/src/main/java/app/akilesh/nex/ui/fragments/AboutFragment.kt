package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.akilesh.nex.BuildConfig
import app.akilesh.nex.Const.Path.modulePath
import app.akilesh.nex.databinding.FragmentAboutBinding
import com.topjohnwu.superuser.Shell


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.version.text = BuildConfig.VERSION_NAME

        val outputs = mutableListOf<String>()
        val propPath = modulePath + "module.prop"
        Shell.su("[ -f $propPath ] && echo y").to(outputs).exec()
        if(outputs.isNotEmpty() && outputs.component1().isNotBlank()) {
            Shell.su("sed -n s/^version=//p $propPath | head -n 1").to(outputs).exec()
            binding.nexVersion.text = outputs.component2().drop(1)
        }
        else {
            binding.nexVersion.text = String.format("%s", "Module not installed")
            Log.e(TAG, "$propPath doesn't exist")
        }

    }

    companion object {

        internal const val TAG = "AboutFragment"
    }
}
