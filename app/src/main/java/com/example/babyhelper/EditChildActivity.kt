package com.example.babyhelper

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.babyhelper.data.model.Child

class EditChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // пока пустой layout
        setContentView(R.layout.activity_edit_child)

        val child = intent.getSerializableExtra("child_id") as? Child
        Toast.makeText(this, "Редактируем: ${child?.name}", Toast.LENGTH_SHORT).show()
    }
}