package com.example.babyhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
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
        val toolbar = findViewById<MaterialToolbar>(R.id.customToolbar)

        // Иконка приложения
        toolbar.findViewById<ImageView>(R.id.iconApp).setOnClickListener {
            // Можно сделать, например, переход на главный экран
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Кнопка настроек
        //toolbar.findViewById<ImageButton>(R.id.buttonSettings).setOnClickListener {
       //     startActivity(Intent(this, SettingsActivity::class.java))
        //}

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
            auth.signOut() // Выходим из аккаунта
            // Возвращаемся на экран входа
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
