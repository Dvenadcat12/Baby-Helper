package com.example.babyhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // Проверяем, вошёл ли пользователь
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        // Кнопка Diary
        findViewById<Button>(R.id.buttonDiary).setOnClickListener {
            startActivity(Intent(this, DiaryActivity::class.java))
        }

        // Остальные кнопки
        findViewById<Button>(R.id.buttonTips).setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }

        findViewById<Button>(R.id.buttonNotes).setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        findViewById<Button>(R.id.buttonTimer).setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }

        // Кнопка выхода
        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}