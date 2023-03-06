package com.barges

import androidx.lifecycle.LifecycleOwner
import com.barges.concurency.Executable
import com.barges.vm.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class FirstFragmentVM @Inject constructor(
    override val executable: Executable
) : BaseVM(executable) {

    fun openDialog() {
//        dialogManager.showDialog(DialogManager.Strategy.TOP, DialogManager.DialogConfig())
    }

    private fun testExecuteSuspend(delay: Long) {
        request { akaBackgroundTask(delay) }
            .withLoader()
            .execute()
    }

    val suspendRequest = request { akaBackgroundTask(1000) }
        .withLoader()


    private fun testExecuteFlow() {
        val flow = flow<Void> { akaBackgroundTask(1000) }
        flow
            .request()
            .withLoader()
            .interceptError { true }
            .execute()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        testExecuteFlow()
//        test()
//        testExecuteSuspend(1000)
//        testExecuteSuspend(2000)
//        testExecuteSuspend(3000)
//        testExecuteSuspend(4000)
//        testExecuteSuspend(5000)
//        testExecuteSuspend(6000)
//        testExecuteSuspend(7000)
    }

    private suspend fun akaBackgroundTask(delay: Long): String {
        delay(delay)
        if (delay == 2000L || delay == 5000L)
            suspendRequest.execute()
        return "Result"
    }
}
