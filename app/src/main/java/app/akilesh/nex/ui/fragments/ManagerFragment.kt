package app.akilesh.nex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.akilesh.nex.Const.Feature.featureNames
import app.akilesh.nex.Const.File.addExtraFiles
import app.akilesh.nex.R
import app.akilesh.nex.databinding.FragmentManagerBinding
import app.akilesh.nex.ui.adapter.ManagerAdapter
import app.akilesh.nex.model.Feature
import app.akilesh.nex.utils.ManagerUtil

class ManagerFragment: Fragment() {

    private var fInstalled = listOf<Feature>()
    private var fNotInstalled = listOf<Feature>()
    private lateinit var binding: FragmentManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManagerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val util = ManagerUtil()
        util.initLists()
        addExtraFiles()

        fInstalled = util.installed.map {
           Feature(featureNames[it], "Remove", ResourcesCompat.getDrawable(resources, R.drawable.ic_delete, requireContext().theme), ResourcesCompat.getColorStateList(resources, R.color.remove, requireContext().theme))
        }

        fNotInstalled = util.notInstalled.map {
            Feature(featureNames[it], "Install", ResourcesCompat.getDrawable(resources, R.drawable.ic_install, requireContext().theme), ResourcesCompat.getColorStateList(resources, R.color.install , requireContext().theme))
        }

        binding.recyclerViewInstalled.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ManagerAdapter(fInstalled)
        }

        binding.recyclerViewNotInstalled.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ManagerAdapter(fNotInstalled)
        }
    }
}
