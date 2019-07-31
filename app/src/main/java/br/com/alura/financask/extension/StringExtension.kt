package br.com.alura.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToCalendar(): Calendar {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val convertedDate = simpleDateFormat.parse(this)
    val date = Calendar.getInstance()
    date.time = convertedDate
    return date
}