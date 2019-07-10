package br.com.alura.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
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
                Transaction(total = BigDecimal(230.5), type = TransactionType.INCOME))

        activity_transactions_list_view.adapter = TransactionsListAdapter(transactions, this)
    }

}