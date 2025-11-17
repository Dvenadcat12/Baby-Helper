package com.example.babyhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
        //toolbar.findViewById<ImageButton>(R.id.buttonSettings).setOnClickListener {
        //    startActivity(Intent(this, SettingsActivity::class.java))
       // }

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

        // Кнопка выхода
        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
