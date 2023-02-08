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
    private var mainTimerIsActive = false
    private var secondaryTimerIsActive = false

    private val scope = CoroutineScope(Dispatchers.Main)
    private var jobMain: Job? = null
    private var jobSecondary: Job? = null
    private var mainTimer: String = convertTimeToDisplay(0L)
    private var secondaryTimer: String = convertTimeToDisplay(0L)

    private val mainStartButtonClickListener: View.OnClickListener = View.OnClickListener {
        binding.mainPauseButton.isClickable = true
        mainStart()
    }

    private val secondaryStartButtonClickListener: View.OnClickListener = View.OnClickListener {
        binding.secondaryPauseButton.isClickable = true
        secondaryStart()
    }

    private val mainStopButtonClickListener: View.OnClickListener = View.OnClickListener {
        mainStop()
    }

    private val secondaryStopButtonClickListener: View.OnClickListener = View.OnClickListener {
        secondaryStop()
    }

    private val mainPauseButtonClickListener: View.OnClickListener = View.OnClickListener {
        if (mainTimerIsActive) {
            mainPause()
        } else {
            mainStart()
        }
    }

    private val secondaryPauseButtonClickListener: View.OnClickListener = View.OnClickListener {
        if (secondaryTimerIsActive) {
            secondaryPause()
        } else {
            secondaryStart()
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
            mainStartButton.setOnClickListener(mainStartButtonClickListener)
            mainStopButton.setOnClickListener(mainStopButtonClickListener)
            mainPauseButton.setOnClickListener(mainPauseButtonClickListener)
            mainPauseButton.isClickable = false
            mainTimerDisplay.text = mainTimer
            secondaryStartButton.setOnClickListener(secondaryStartButtonClickListener)
            secondaryStopButton.setOnClickListener(secondaryStopButtonClickListener)
            secondaryPauseButton.setOnClickListener(secondaryPauseButtonClickListener)
            secondaryPauseButton.isClickable = false
            secondaryTimerDisplay.text = secondaryTimer
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]
    }

    private fun convertTimeToDisplay(time: Long): String {
        return "${time / 100}:${(time % 100 - time % 10) / 10}${time % 10}"
    }

    private fun mainStart() {
        binding.mainStartButton.isClickable = false
        viewModel.activateMainTimer()
        jobMain = scope.launch {
            mainTimerIsActive = true
            while (mainTimerIsActive) {
                mainTimer = convertTimeToDisplay(viewModel.getMainTime())
                binding.mainTimerDisplay.text = mainTimer
                delay(10)
            }
        }
    }

    private fun mainPause() {
        viewModel.pauseMainTimer()
        jobMain?.cancel()
        mainTimerIsActive = false
        binding.mainStartButton.isClickable = true
    }

    private fun mainStop() {
        viewModel.stopMainTimer()
        jobMain?.cancel()
        mainTimerIsActive = false
        mainTimer = convertTimeToDisplay(0L)
        binding.mainTimerDisplay.text = mainTimer
        binding.mainStartButton.isClickable = true
        binding.mainPauseButton.isClickable = false
    }

    private fun secondaryStart() {
        binding.secondaryStartButton.isClickable = false
        viewModel.activateSecondaryTimer()
        jobSecondary = scope.launch {
            secondaryTimerIsActive = true
            while (secondaryTimerIsActive) {
                secondaryTimer = convertTimeToDisplay(viewModel.getSecondaryTime())
                binding.secondaryTimerDisplay.text = secondaryTimer
                delay(10)
            }
        }
    }

    private fun secondaryPause() {
        viewModel.pauseSecondaryTimer()
        jobSecondary?.cancel()
        secondaryTimerIsActive = false
        binding.secondaryStartButton.isClickable = true
    }

    private fun secondaryStop() {
        viewModel.stopSecondaryTimer()
        jobSecondary?.cancel()
        secondaryTimerIsActive = false
        secondaryTimer = convertTimeToDisplay(0L)
        binding.secondaryTimerDisplay.text = secondaryTimer
        binding.secondaryStartButton.isClickable = true
        binding.secondaryPauseButton.isClickable = false
    }
}