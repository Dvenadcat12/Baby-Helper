package com.example.babyhelper

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    private lateinit var textTimer: TextView
    private lateinit var startButton: Button
    private lateinit var numberPicker: NumberPicker
    private var timerRunning = false
    private var countDownTimer: CountDownTimer? = null
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        textTimer = findViewById(R.id.textTimer)
        startButton = findViewById(R.id.buttonStartTimer)
        numberPicker = findViewById(R.id.numberPickerMinutes)
        mediaPlayer = MediaPlayer.create(this, R.raw.timer_sound) // добавьте файл timer_sound.mp3 в res/raw

        startButton.setOnClickListener {
            if (!timerRunning) startTimer() else stopTimer()
        }
    }

    private fun startTimer() {
        val minutes = numberPicker.value
        val duration = minutes * 60 * 1000L
        numberPicker.minValue = 1
        numberPicker.maxValue = 60
        numberPicker.value = 10 // можно задать значение по умолчанию

        countDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutesLeft = (millisUntilFinished / 1000) / 60
                textTimer.text = String.format("%02d:%02d", minutesLeft, seconds)
            }

            override fun onFinish() {
                textTimer.text = "00:00"
                timerRunning = false
                startButton.text = "Запустить таймер"
                mediaPlayer.start()
            }
        }.start()
        timerRunning = true
        startButton.text = "Остановить таймер"
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        timerRunning = false
        textTimer.text = "00:00"
        startButton.text = "Запустить таймер"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
