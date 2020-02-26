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
import app.akilesh.nex.ui.adapter.ManagerAdapter
import app.akilesh.nex.model.Feature
import app.akilesh.nex.utils.ManagerUtil
import kotlinx.android.synthetic.main.fragment_manager.*

class ManagerFragment: Fragment() {

    private var fInstalled = listOf<Feature>()

    private var fNotInstalled = listOf<Feature>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val util = ManagerUtil()
        util.initLists()
        addExtraFiles()

        fInstalled = util.installed.map {
           Feature(featureNames[it], "Remove", ResourcesCompat.getDrawable(resources, R.drawable.ic_delete, null), ResourcesCompat.getColorStateList(resources, R.color.remove, null))
        }

        fNotInstalled = util.notInstalled.map {
            Feature(featureNames[it], "Install", ResourcesCompat.getDrawable(resources, R.drawable.ic_install, null), ResourcesCompat.getColorStateList(resources, R.color.install , null))
        }

        recyclerView_installed.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ManagerAdapter(fInstalled)
        }

        recyclerView_not_installed.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ManagerAdapter(fNotInstalled)
        }

    }

    companion object {
        internal const val TAG = "ManagerFragment"
    }
}