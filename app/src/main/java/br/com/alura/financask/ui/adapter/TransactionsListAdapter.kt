package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import br.com.alura.financask.extension.formatDate
import br.com.alura.financask.extension.formatToBrazilCurrency
import br.com.alura.financask.extension.layoutInflater
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionsListAdapter(private val transactions: List<Transaction>,
                              private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        val newView = view ?: context.layoutInflater
                .inflate(R.layout.transaction_item, parent, false)

        val transaction = transactions[position]

        if (transaction.type == TransactionType.INCOME) {
            setupIncomeView(newView)
        } else {
            setupOutcomeView(newView)
        }

        newView.transaction_value.text = transaction.total.formatToBrazilCurrency()
        newView.transaction_category.text = transaction.category
        newView.transaction_date.text = transaction.date.formatDate()

        return newView
    }

    private fun setupOutcomeView(newView: View) {
        newView.transaction_value.setTextColor(ContextCompat.getColor(context, R.color.outcome))
        newView.transaction_icon.setBackgroundResource(R.drawable.transaction_outcome_icon)
    }

    private fun setupIncomeView(newView: View) {
        newView.transaction_value.setTextColor(ContextCompat.getColor(context, R.color.income))
        newView.transaction_icon.setBackgroundResource(R.drawable.transaction_income_icon)
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactions.size
    }

}