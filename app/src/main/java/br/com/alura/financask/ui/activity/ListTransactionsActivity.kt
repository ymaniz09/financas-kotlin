package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
import br.com.alura.financask.ui.SummaryView
import br.com.alura.financask.ui.adapter.TransactionsListAdapter
import br.com.alura.financask.ui.dialog.EditTransactionDialog
import br.com.alura.financask.ui.dialog.TransactionDialog
import kotlinx.android.synthetic.main.activity_list_transactions.*

class ListTransactionsActivity : AppCompatActivity() {

    private val transactions: MutableList<Transaction> = mutableListOf()
    private val activityView: View by lazy { window.decorView }
    private val viewGroup by lazy { activityView as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)

        setupListView()
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
        TransactionDialog(viewGroup, this)
                .showDialog(type) {
                    addTransaction(it)
                    activity_transactions_floating_action_button.close(true)
                }
    }

    private fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        updateTransactions()
    }

    private fun updateTransactions() {
        setupListView()
        setupSummary()
    }

    private fun setupListView() {
        with(activity_transactions_list_view) {
            this.adapter = TransactionsListAdapter(transactions, this@ListTransactionsActivity)
            this.setOnItemClickListener { _, _, position, _ ->
                val clickedTransaction = transactions[position]
                setupEditTransaction(clickedTransaction, position)
            }
        }
    }

    private fun setupEditTransaction(clickedTransaction: Transaction, position: Int) {
        EditTransactionDialog(viewGroup, this)
                .showDialog(clickedTransaction) {
                    editTransaction(it, position)
                    }
    }

    private fun editTransaction(transaction: Transaction, position: Int) {
        transactions[position] = transaction
        updateTransactions()
    }

    private fun setupSummary() {
        val summaryView = SummaryView(this, activityView, transactions)
        summaryView.setupSummaryView()
    }
}