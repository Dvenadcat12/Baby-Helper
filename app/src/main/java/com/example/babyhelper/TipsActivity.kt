package com.example.babyhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        val tipsList = listOf(
            "Меняйте подгузник каждые 2–3 часа",
            "Кормите ребенка по требованию",
            "Старайтесь укладывать ребенка спать в одно и то же время",
            "Часто проверяйте температуру в комнате",
            "Общайтесь с ребенком, читайте книги, пойте песни"
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerTips)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TipAdapter(tipsList)
    }
}
