package br.com.alura.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.ui.adapter.TransactionsListAdapter
import kotlinx.android.synthetic.main.activity_list_transactions.*

class ListTransactionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)


        val transacoes = listOf("Comida - R$ 20,50",
                "Economia - R$ 100,00")


        activity_transactions_list_view.adapter = TransactionsListAdapter(transacoes, this)
    }

}