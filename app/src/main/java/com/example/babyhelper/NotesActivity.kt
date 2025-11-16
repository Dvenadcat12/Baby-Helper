package com.example.babyhelper

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {

    private lateinit var editNote: EditText
    private lateinit var buttonSave: Button
    private val PREFS_NAME = "babyhelper_notes"
    private val NOTE_KEY = "note_text"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        editNote = findViewById(R.id.editNote)
        buttonSave = findViewById(R.id.buttonSaveNote)

        // Загружаем сохраненную заметку
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedNote = sharedPref.getString(NOTE_KEY, "")
        editNote.setText(savedNote)

        buttonSave.setOnClickListener {
            val noteText = editNote.text.toString()
            if (noteText.isNotEmpty()) {
                with(sharedPref.edit()) {
                    putString(NOTE_KEY, noteText)
                    apply()
                }
                Toast.makeText(this, "Заметка сохранена!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите текст заметки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
