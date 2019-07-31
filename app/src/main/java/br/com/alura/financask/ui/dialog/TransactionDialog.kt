package br.com.alura.financask.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransactionDelegate
import br.com.alura.financask.extension.convertToCalendar
import br.com.alura.financask.extension.formatDate
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.math.BigDecimal
import java.util.*

class TransactionDialog(private val viewGroup: ViewGroup,
                        private val context: Context) {

    private val layoutView = inflateLayout()

    fun setupDialog(transactionDelegate: TransactionDelegate) {
        setupDate()
        setupCategory()
        setupInputDialog(transactionDelegate)
    }

    private fun setupInputDialog(transactionDelegate: TransactionDelegate) {
        AlertDialog.Builder(context)
                .setTitle(R.string.add_income)
                .setView(layoutView)
                .setPositiveButton("Add"
                ) { _, _ ->
                    val valueString = layoutView.form_transaction_value.text.toString()
                    val dateString = layoutView.form_transaction_date.text.toString()
                    val categoryString = layoutView.form_transaction_category.selectedItem.toString()
                    val total = convertTotalField(valueString)
                    val date = dateString.convertToCalendar()

                    val newTransaction = Transaction(type = TransactionType.INCOME,
                            total = total,
                            date = date,
                            category = categoryString)

                    transactionDelegate.delegate(newTransaction)

                }
                .setNegativeButton("Cancel", null)
                .show()
    }

    private fun convertTotalField(valueString: String): BigDecimal {
        return try {
            BigDecimal(valueString)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Error parsing value", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun setupCategory() {
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.income_category,
                android.R.layout.simple_spinner_dropdown_item)

        layoutView.form_transaction_category.adapter = adapter
    }

    private fun setupDate() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        layoutView.form_transaction_date.setText(today.formatDate())
        layoutView.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                layoutView.form_transaction_date.setText(selectedDate.formatDate())
            }, year, month, day)
                    .show()
        }
    }

    private fun inflateLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transaction, viewGroup, false)
    }
}