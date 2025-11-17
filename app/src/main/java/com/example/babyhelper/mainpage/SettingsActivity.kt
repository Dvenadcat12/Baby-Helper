package com.example.babyhelper.mainpage

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.babyhelper.R
import com.example.babyhelper.loginpage.LoginActivity
import com.example.babyhelper.mainpage.settingspage.ChildMenuActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {

    private lateinit var btnTheme: TextView
    private lateinit var btnNotifications: TextView
    private lateinit var btnLanguage: TextView
    private lateinit var btnBackup: TextView
    private lateinit var btnLogout: TextView
    private lateinit var btnChildMenu: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        auth = FirebaseAuth.getInstance()

        // Привязка к элементам
        btnTheme = findViewById(R.id.btnTheme)
        btnNotifications = findViewById(R.id.btnNotifications)
        btnLanguage = findViewById(R.id.btnLanguage)
        btnBackup = findViewById(R.id.btnBackup)
        btnLogout = findViewById(R.id.btnLogout)
        btnChildMenu = findViewById(R.id.btnChildMenu)

        // Обработчики нажатий
        btnTheme.setOnClickListener {
            Toast.makeText(this, "Смена темы (еще не реализовано)", Toast.LENGTH_SHORT).show()
        }

        btnNotifications.setOnClickListener {
            Toast.makeText(this, "Уведомления (еще не реализовано)", Toast.LENGTH_SHORT).show()
        }

        btnLanguage.setOnClickListener {
            Toast.makeText(this, "Смена языка (еще не реализовано)", Toast.LENGTH_SHORT).show()
        }

        btnBackup.setOnClickListener {
            Toast.makeText(this, "Резервное копирование (еще не реализовано)", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnChildMenu.setOnClickListener {
            val intent = Intent(this, ChildMenuActivity::class.java)
            startActivity(intent)
        }
    }
}
