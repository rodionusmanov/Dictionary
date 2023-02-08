package com.example.dictionary.timer.model

import kotlinx.coroutines.*

class TimerRepo : ITimerRepo {

    private var time: Long = 0L
    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    override fun startTimer() {
        job = scope.launch {
            while (true) {
                time += 1L
                delay(10)
            }
        }
    }

    override fun stopTimer() {
        job?.cancel()
        time = 0L
    }

    fun getTime(): Long {
        return time
    }

    override fun pauseTimer() {
        job?.cancel()
    }
}