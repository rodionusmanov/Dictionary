package com.example.dictionary.timer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.databinding.ActivityTimerBinding
import com.example.dictionary.timer.viewModel.TimerViewModel
import kotlinx.coroutines.*

class TimerActivity : AppCompatActivity() {

    private lateinit var viewModel: TimerViewModel
    private lateinit var binding: ActivityTimerBinding
    private var timerIsActive = false

    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null
    private var mainTimer: String = convertTimeToDisplay(0L)

    private val startButtonClickListener: View.OnClickListener = View.OnClickListener {
        binding.pauseButton.isClickable = true
        mainStart()
    }

    private val stopButtonClickListener: View.OnClickListener = View.OnClickListener {
        mainStop()
    }

    private val pauseButtonClickListener: View.OnClickListener = View.OnClickListener {
        if (timerIsActive) {
            mainPause()
        } else {
            mainStart()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    private fun initViews() {
        binding.apply {
            startButton.setOnClickListener(startButtonClickListener)
            stopButton.setOnClickListener(stopButtonClickListener)
            pauseButton.setOnClickListener(pauseButtonClickListener)
            pauseButton.isClickable = false
            timerDisplay.text = mainTimer
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]
    }

    private fun convertTimeToDisplay(time: Long): String {
        return "${time / 100}:${(time % 100 - time % 10) / 10}${time % 10}"
    }

    private fun mainStart() {
        binding.startButton.isClickable = false
        viewModel.activateMainTimer()
        job = scope.launch {
            timerIsActive = true
            while (timerIsActive) {
                mainTimer = convertTimeToDisplay(viewModel.getMainTime())
                binding.timerDisplay.text = mainTimer
                delay(10)
            }
        }
    }

    private fun mainPause() {
        viewModel.pauseMainTimer()
        job?.cancel()
        timerIsActive = false
        binding.startButton.isClickable = true
    }

    private fun mainStop() {
        viewModel.stopMainTimer()
        timerIsActive = false
        mainTimer = convertTimeToDisplay(0L)
        binding.timerDisplay.text = mainTimer
        binding.startButton.isClickable = true
        binding.pauseButton.isClickable = false
    }
}