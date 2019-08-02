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

class EditTransactionDialog(private val viewGroup: ViewGroup,
                            private val context: Context) {

    private val layoutView = inflateLayout()
    private val dateEditText = layoutView.form_transaction_date
    private val categorySpinner = layoutView.form_transaction_category
    private val valueEditText = layoutView.form_transaction_value

    fun showDialog(transaction: Transaction, transactionDelegate: TransactionDelegate) {
        val type = transaction.type

        setupDate()
        setupCategory(type)
        setupInputDialog(type, transactionDelegate)
         
        valueEditText.setText(transaction.total.toString())
        dateEditText.setText(transaction.date.formatDate())
        val categories = context.resources.getStringArray(getCategoryByTransactionType(transaction.type))
        val indexOfCategory = categories.indexOf(transaction.category)
        categorySpinner.setSelection(indexOfCategory, true)
    }

    private fun setupInputDialog(type: TransactionType, transactionDelegate: TransactionDelegate) {
        val title = getTitleByTransactionType(type)

        AlertDialog.Builder(context)
                .setTitle(title)
                .setView(layoutView)
                .setPositiveButton("Edit"
                ) { _, _ ->
                    val valueString = valueEditText.text.toString()
                    val dateString = dateEditText.text.toString()
                    val categoryString = categorySpinner.selectedItem.toString()
                    val total = convertTotalField(valueString)
                    val date = dateString.convertToCalendar()

                    val newTransaction = Transaction(type = type,
                            total = total,
                            date = date,
                            category = categoryString)

                    transactionDelegate.delegate(newTransaction)

                }
                .setNegativeButton("Cancel", null)
                .show()
    }

    private fun getTitleByTransactionType(type: TransactionType): Int {
        return if (type == TransactionType.INCOME) {
            R.string.change_income
        } else {
            R.string.change_outgo
        }
    }

    private fun convertTotalField(valueString: String): BigDecimal {
        return try {
            BigDecimal(valueString)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Error parsing value", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun setupCategory(type: TransactionType) {
        val category = getCategoryByTransactionType(type)

        val adapter = ArrayAdapter.createFromResource(context,
                category,
                android.R.layout.simple_spinner_dropdown_item)

        categorySpinner.adapter = adapter
    }

    private fun getCategoryByTransactionType(type: TransactionType): Int {
        return if (type == TransactionType.INCOME) {
            R.array.income_category
        } else {
            R.array.outgo_category
        }
    }

    private fun setupDate() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        dateEditText.setText(today.formatDate())
        layoutView.setOnClickListener {
            DatePickerDialog(context,
                    { _, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        dateEditText.setText(selectedDate.formatDate())
                    },
                    year, month, day)
                    .show()
        }
    }

    private fun inflateLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transaction, viewGroup, false)
    }
}