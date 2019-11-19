package app.akilesh.nex.utils

import android.util.Log
import app.akilesh.nex.Const.Path.updateHistoryPath
import app.akilesh.nex.data.UpdateHistory
import com.topjohnwu.superuser.Shell

class UpdateHistoryUtil {
    var updateList = listOf<UpdateHistory>()
    private var time = ""
    private var date = ""

    fun check(): Boolean {
        val op = Shell.su("[ -f $updateHistoryPath ] && echo y").exec().out
        return op.isNotEmpty() && op.component1().isNotBlank()
    }

    fun init(){
        val output = mutableListOf<String>()
        Shell.su("cat $updateHistoryPath").to(output).exec()

        val nonNullOutput = output.filterNot { it == "" }
                .filterNot { it.length < 50 }

        var p = listOf<String>()
        nonNullOutput.forEach {
            p = p + it.split(":", "->")
        }
        p = p.filterNot { it == "SWUPG" }
        val q = p.chunked(3)

        q.forEach {
            toDateTime(it.component1())
            updateList = updateList + UpdateHistory(date, time, it.component2(), it.component3())
        }
        Log.d("test", updateList.toString())
    }

    private fun toDateTime(s: String){
        date = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8)
        time = s.substring(8, 10) + ":" + s.substring(10, 12) + ":" + s.substring(12, s.length)
    }
}