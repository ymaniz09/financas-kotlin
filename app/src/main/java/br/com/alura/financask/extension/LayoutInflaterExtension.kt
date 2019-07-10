package br.com.alura.financask.extension

import android.content.Context
import android.view.LayoutInflater

val Context.layoutInflater get() = LayoutInflater.from(this)