package com.example.babyhelper.mainpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.babyhelper.NotesActivity
import com.example.babyhelper.R
import com.example.babyhelper.TimerActivity
import com.example.babyhelper.TipsActivity
import com.example.babyhelper.loginpage.LoginActivity
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

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)

        // Иконка приложения слева
        toolbar.findViewById<ImageView>(R.id.toolbarLogo).setOnClickListener {
            // Можно сделать переход на главный экран (текущий)
        }

            // Кнопка настроек справа
        val settingsButton = findViewById<ImageButton>(R.id.buttonSettings)

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Основные кнопки
        findViewById<Button>(R.id.buttonTips).setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }

        findViewById<Button>(R.id.buttonNotes).setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        findViewById<Button>(R.id.buttonTimer).setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
    }
}