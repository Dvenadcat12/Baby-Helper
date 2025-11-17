package com.example.babyhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class CalendarAdapter(
    private val days: ArrayList<LocalDate?>,
    private val currentDate: LocalDate,
    private val onDayClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayOfMonth: TextView = itemView.findViewById(R.id.calendarDayNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = days[position]

        if (date == null) {
            holder.dayOfMonth.text = ""
            holder.itemView.isEnabled = false
            return
        }

        holder.dayOfMonth.text = date.dayOfMonth.toString()

        // Подсветка сегодняшнего дня - ИСПРАВЛЕНО!
        if (date == LocalDate.now()) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.today_highlight)
            )
            holder.dayOfMonth.setTextColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.white)
            )
        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.transparent)
            )
            holder.dayOfMonth.setTextColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.black)
            )
        }

        // Подсветка выбранного месяца
        if (date.month != currentDate.month) {
            holder.dayOfMonth.setTextColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.darker_gray)
            )
        }

        holder.itemView.setOnClickListener {
            onDayClick(date)
        }
    }

    override fun getItemCount(): Int = days.size
}