package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransactionDelegate
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
import br.com.alura.financask.ui.SummaryView
import br.com.alura.financask.ui.adapter.TransactionsListAdapter
import br.com.alura.financask.ui.dialog.TransactionDialog
import kotlinx.android.synthetic.main.activity_list_transactions.*

class ListTransactionsActivity : AppCompatActivity() {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)

        setupAdapter()
        setupSummary()
        setupFab()
    }

    private fun setupFab() {
        activity_transactions_floating_action_button_add_income.setOnClickListener {
            setupTransactionDialog(TransactionType.INCOME)
        }

        activity_transactions_floating_action_button_add_outgo.setOnClickListener {
            setupTransactionDialog(TransactionType.OUTGO)
        }
    }

    private fun setupTransactionDialog(type: TransactionType) {
        TransactionDialog(window.decorView as ViewGroup, this)
                .showDialog(type, object : TransactionDelegate {
                    override fun delegate(transaction: Transaction) {
                        updateTransactions(transaction)
                        activity_transactions_floating_action_button.close(true)
                    }
                })
    }

    private fun updateTransactions(transaction: Transaction) {
        transactions.add(transaction)
        setupAdapter()
        setupSummary()
    }

    private fun setupAdapter() {
        activity_transactions_list_view.adapter = TransactionsListAdapter(transactions, this)
    }

    private fun setupSummary() {
        val view = window.decorView
        val summaryView = SummaryView(this, view, transactions)
        summaryView.setupSummaryView()
    }
}