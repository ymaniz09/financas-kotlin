package br.com.alura.financask.ui.model

import java.math.BigDecimal
import java.util.*

class Transaction(val total: BigDecimal,
                  val category: String,
                  val date: Calendar)