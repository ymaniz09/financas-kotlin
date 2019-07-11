package br.com.alura.financask.ui

import android.view.View
import br.com.alura.financask.extension.formatToBrazilCurrency
import br.com.alura.financask.model.Summary
import br.com.alura.financask.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*

class SummaryView(private val view: View,
                  transactions: List<Transaction>) {

    private val summary = Summary(transactions)

    fun setupIncome() {
        val total = summary.income()
        view.income_summary.text = total.formatToBrazilCurrency()
    }

    fun setupOutgo() {
        val total = summary.outgo()
        view.outgo_summary.text = total.formatToBrazilCurrency()
    }

    fun setupTotal() {
        val total = summary.total()
        view.total_summary.text = total.formatToBrazilCurrency()
    }
}