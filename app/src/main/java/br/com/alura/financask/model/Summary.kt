package br.com.alura.financask.model

import java.math.BigDecimal

class Summary(private val transactions: List<Transaction>) {
    fun income(): BigDecimal {
        var total = BigDecimal.ZERO

        for (transaction in transactions) {
            if (transaction.type == TransactionType.INCOME) {
                total = total.plus(transaction.total)
            }
        }

        return total
    }

    fun outgo(): BigDecimal {
        var total = BigDecimal.ZERO

        for (transaction in transactions) {
            if (transaction.type == TransactionType.OUTGO) {
                total = total.plus(transaction.total)
            }
        }

        return total
    }

    fun total(): BigDecimal {
        return income().subtract(outgo())
    }
}