package com.example.babyhelper.ui.sleep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.babyhelper.data.db.SleepEntry
import com.example.babyhelper.databinding.ItemSleepBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Duration

class SleepAdapter(private val onClick: (SleepEntry) -> Unit) :
    ListAdapter<SleepEntry, SleepAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SleepEntry>() {
            override fun areItemsTheSame(oldItem: SleepEntry, newItem: SleepEntry) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: SleepEntry, newItem: SleepEntry) = oldItem == newItem
        }
    }

    inner class VH(private val binding: ItemSleepBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SleepEntry) {
            val start = Instant.ofEpochMilli(item.startTime).atZone(ZoneId.systemDefault())
            val end = item.endTime?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()) }

            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            binding.tvDate.text = start.format(dateFormatter)
            binding.tvTime.text = if (end != null) "${start.format(timeFormatter)} - ${end.format(timeFormatter)}" else "${start.format(timeFormatter)} - ..."
            binding.tvDuration.text = item.durationMillis?.let {
                val d = Duration.ofMillis(it)
                val hh = d.toHours()
                val mm = d.minusHours(hh).toMinutes()
                "${hh}ч ${mm}м"
            } ?: "—"
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemSleepBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))
}
