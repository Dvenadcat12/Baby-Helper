package com.example.babyhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.babyhelper.data.model.Child
import com.example.babyhelper.R

class ChildAdapter(
    private val onEdit: (Child) -> Unit,
    private val onDelete: (Child) -> Unit
) : ListAdapter<Child, ChildAdapter.ChildViewHolder>(ChildDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_child, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = getItem(position)
        holder.bind(child)
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvChildName)
        private val tvAge: TextView = itemView.findViewById(R.id.tvChildAge)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(child: Child) {
            tvName.text = child.name

            btnEdit.setOnClickListener { onEdit(child) }
            btnDelete.setOnClickListener { onDelete(child) }
        }
    }
}

class ChildDiffCallback : DiffUtil.ItemCallback<Child>() {
    override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem == newItem
    }
}
