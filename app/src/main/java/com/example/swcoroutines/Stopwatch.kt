package com.example.swcoroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.Job

class Stopwatch() {
    private var timeInSeconds = 0
    private var job: Job? = null

    fun start(onTick: (Int) -> Unit) {
        if (job == null) {
            job = CoroutineScope(Dispatchers.Main).launch {
                while (isActive) {
                    delay(1000L)
                    timeInSeconds++
                    onTick(timeInSeconds)
                }
            }
        }
    }

    fun pause() {
        job?.cancel()
        job = null
    }

    fun continueTimer(onTick: (Int) -> Unit) {
        start(onTick)
    }

    fun reset(onTick: (Int) -> Unit) {
        pause()
        timeInSeconds = 0
        onTick(timeInSeconds)
    }

    fun getTime(): Int = timeInSeconds
}