package br.com.alura.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
import br.com.alura.financask.ui.SummaryView
import br.com.alura.financask.ui.adapter.TransactionsListAdapter
import kotlinx.android.synthetic.main.activity_list_transactions.*
import java.math.BigDecimal

class ListTransactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)


        val transactions = listOf(
                Transaction(BigDecimal(20.5), "Food"),
                Transaction(BigDecimal(30.5), "Uber"),
                Transaction(BigDecimal(130.5)),
                Transaction(total = BigDecimal(230.5), type = TransactionType.INCOME),
                Transaction(category = "A really big category name", total = BigDecimal(230.5), type = TransactionType.INCOME),
                Transaction(BigDecimal(1999.99), "Food"))

        activity_transactions_list_view.adapter = TransactionsListAdapter(transactions, this)

        setupSummary(transactions)
    }

    private fun setupSummary(transactions: List<Transaction>) {
        val view = window.decorView
        val summaryView = SummaryView(this, view, transactions)
        summaryView.setupIncome()
        summaryView.setupOutgo()
        summaryView.setupTotal()
    }

}