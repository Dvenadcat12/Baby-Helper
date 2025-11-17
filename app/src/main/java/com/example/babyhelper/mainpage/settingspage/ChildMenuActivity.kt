package com.example.babyhelper.mainpage.settingspage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babyhelper.mainpage.settingspage.AddChildActivity
import com.example.babyhelper.ChildAdapter
import com.example.babyhelper.EditChildActivity
import com.example.babyhelper.data.db.ChildDatabase
import com.example.babyhelper.databinding.ActivityChildMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChildMenuBinding
    private lateinit var adapter: ChildAdapter
    private val db by lazy { ChildDatabase.Companion.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChildAdapter(
            onEdit = { child ->
                val intent = Intent(this, EditChildActivity::class.java)
                intent.putExtra("child_id", child.id)
                startActivity(intent)
            },
            onDelete = { child ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.childDao().delete(child)
                    loadChildren()
                }
            }
        )

        binding.childrenRecycler.layoutManager = LinearLayoutManager(this)
        binding.childrenRecycler.adapter = adapter

        binding.btnAddChild.setOnClickListener {
            startActivity(Intent(this, AddChildActivity::class.java))
        }

        loadChildren()
    }

    override fun onResume() {
        super.onResume()
        loadChildren()
    }

    private fun loadChildren() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = db.childDao().getAllChildren()
            runOnUiThread {
                adapter.submitList(list)
            }
        }
    }
}