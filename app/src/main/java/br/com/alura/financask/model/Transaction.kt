package br.com.alura.financask.model

import java.math.BigDecimal
import java.util.*

class Transaction(val total: BigDecimal,
                  val category: String = "undefined",
                  val type: TransactionType = TransactionType.OUTGO,
                  val date: Calendar = Calendar.getInstance())