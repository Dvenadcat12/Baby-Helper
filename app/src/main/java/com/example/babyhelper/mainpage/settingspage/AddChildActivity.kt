package com.example.babyhelper.mainpage.settingspage

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.babyhelper.R
import com.example.babyhelper.data.db.ChildDatabase
import com.example.babyhelper.data.model.Child
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddChildActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etBirthDate: EditText
    private lateinit var btnSave: Button

    private val db by lazy { ChildDatabase.Companion.getDatabase(this) }

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

        // Привязка элементов
        etName = findViewById(R.id.etChildName)
        etBirthDate = findViewById(R.id.etChildBirthDate)
        btnSave = findViewById(R.id.btnSaveChild)

        // Показать DatePicker при клике на поле даты рождения
        etBirthDate.setOnClickListener {
            showDatePicker()
        }

        btnSave.setOnClickListener {
            saveChild()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)
                etBirthDate.setText(dateFormat.format(selectedCalendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun saveChild() {
        val name = etName.text.toString().trim()
        val birthDateStr = etBirthDate.text.toString().trim()

        if (name.isEmpty() || birthDateStr.isEmpty()) {
            Toast.makeText(this, "Введите имя и дату рождения ребёнка", Toast.LENGTH_SHORT).show()
            return
        }

        val birthDate = try {
            dateFormat.parse(birthDateStr)
        } catch (e: Exception) {
            Toast.makeText(this, "Введите корректную дату рождения", Toast.LENGTH_SHORT).show()
            return
        }

        val newChild = Child(
            name = name,
            birthDate = dateFormat.format(birthDate!!)
        )

        CoroutineScope(Dispatchers.IO).launch {
            db.childDao().insert(newChild)
            runOnUiThread {
                Toast.makeText(this@AddChildActivity, "Ребёнок добавлен", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}