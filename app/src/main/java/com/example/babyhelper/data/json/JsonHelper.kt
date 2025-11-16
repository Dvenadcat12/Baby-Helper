package com.example.babyhelper.data.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Tip(val title: String, val text: String)

fun readJsonFromAssets(context: Context, filename: String = "tips.json"): List<Tip> {
    val json = context.assets.open(filename).bufferedReader().use { it.readText() }
    val gson = Gson()
    val type = object : TypeToken<List<Tip>>() {}.type
    return gson.fromJson(json, type)
}
