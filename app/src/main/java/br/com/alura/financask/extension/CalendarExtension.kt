package br.com.alura.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(this.time)
}