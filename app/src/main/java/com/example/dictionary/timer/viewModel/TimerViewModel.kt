package com.example.dictionary.timer.viewModel

import androidx.lifecycle.ViewModel
import com.example.dictionary.timer.model.TimerRepo
import kotlinx.coroutines.*

class TimerViewModel : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
    )
    private val mainTimerRepo = TimerRepo()

    fun activateMainTimer() {
        mainTimerRepo.startTimer()
    }

    fun pauseMainTimer() {
        mainTimerRepo.pauseTimer()
    }

    fun stopMainTimer(){
        mainTimerRepo.stopTimer()
    }

    suspend fun getMainTime(): Long {
        viewModelCoroutineScope.launch {
            getTime(mainTimerRepo)
        }
        return mainTimerRepo.getTime()
    }

    private suspend fun getTime(timerRepo: TimerRepo): Long {
        withContext(Dispatchers.IO) {
            println("VVV ${timerRepo.getTime()}")
        }
        return timerRepo.getTime()
    }
}