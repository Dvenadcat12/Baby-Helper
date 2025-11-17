package com.example.babyhelper

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class DiaryActivity : AppCompatActivity() {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private var currentDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        setupToolbar()
        setupViews()
        setupCalendar()
    }

    private fun setupToolbar() {
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            title = "Baby Diary"
            setNavigationOnClickListener { finish() }
        }
    }

    private fun setupViews() {
        monthYearText = findViewById(R.id.monthYearText)
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)

        findViewById<TextView>(R.id.previousMonthBtn).setOnClickListener {
            currentDate = currentDate.minusMonths(1)
            setupCalendar()
        }

        findViewById<TextView>(R.id.nextMonthBtn).setOnClickListener {
            currentDate = currentDate.plusMonths(1)
            setupCalendar()
        }
    }

    private fun setupCalendar() {
        monthYearText.text = currentDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"))

        val daysInMonth = daysInMonthArray(currentDate)
        calendarRecyclerView.layoutManager = GridLayoutManager(this, 7)
        calendarRecyclerView.adapter = CalendarAdapter(daysInMonth, currentDate) { day ->
            // ВРЕМЕННО: просто показываем Toast при клике на день
            Toast.makeText(this, "Выбран день: $day", Toast.LENGTH_SHORT).show()
            // DayDetailFragment добавим позже
        }
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<LocalDate?> {
        val daysInMonthArray = ArrayList<LocalDate?>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        // Пустые ячейки для дней предыдущего месяца
        for (i in 1 until dayOfWeek) {
            daysInMonthArray.add(null)
        }

        // Дни текущего месяца
        for (i in 1..daysInMonth) {
            daysInMonthArray.add(LocalDate.of(currentDate.year, currentDate.month, i))
        }

        return daysInMonthArray
    }
}