package br.com.alura.financask.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import br.com.alura.financask.extension.formatToBrazilCurrency
import br.com.alura.financask.model.Summary
import br.com.alura.financask.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class SummaryView(context: Context,
                  private val view: View,
                  transactions: List<Transaction>) {

    private val summary = Summary(transactions)

    private val incomeColor = ContextCompat.getColor(context, R.color.income)
    private val outgoColor = ContextCompat.getColor(context, R.color.outgo)

    private fun setupIncome() {
        val total = summary.income
        with(view.income_summary) {
            text = total.formatToBrazilCurrency()
            setTextColor(incomeColor)
        }
    }

    private fun setupOutgo() {
        val total = summary.outgo
        with(view.outgo_summary) {
            text = total.formatToBrazilCurrency()
            setTextColor(outgoColor)
        }
    }

    private fun setupTotal() {
        val total = summary.total
        val color = getColorByValue(total)
        with(view.total_summary) {
            text = total.formatToBrazilCurrency()
            setTextColor(color)
        }
    }

    private fun getColorByValue(total: BigDecimal): Int {
        return if (total < BigDecimal.ZERO) {
            outgoColor
        } else {
            incomeColor
        }
    }

    fun setupSummaryView() {
        setupIncome()
        setupOutgo()
        setupTotal()
    }
}