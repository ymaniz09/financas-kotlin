package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.ui.model.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class TransactionsListAdapter(private val transactions: List<Transaction>,
                              private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val newView = LayoutInflater.from(context)
                .inflate(R.layout.transaction_item, parent, false)

        val transaction = transactions[position]

        newView.transaction_value.text = transaction.total.toEngineeringString()
        newView.transaction_category.text = transaction.category


        newView.transaction_date.text =
                SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(transaction.date.time)

        return newView
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