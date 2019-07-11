package br.com.alura.financask.model

import java.math.BigDecimal

class Summary(private val transactions: List<Transaction>) {
    val income get() = sumByTransactionType(TransactionType.INCOME)

    val outgo get() = sumByTransactionType(TransactionType.OUTGO)

    val total: BigDecimal get() = income.subtract(outgo)

    private fun sumByTransactionType(transactionType: TransactionType): BigDecimal {
        return BigDecimal(transactions
                .filter { it.type == transactionType }
                .sumByDouble { it.total.toDouble() })
    }
}