package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.delegate.TransactionDelegate
import br.com.alura.financask.extension.formatDate
import br.com.alura.financask.model.Transaction
import br.com.alura.financask.model.TransactionType

class EditTransactionDialog(viewGroup: ViewGroup,
                            private val context: Context) : BaseDialog(context, viewGroup) {
    override val positiveButtonTitle: String
        get() = "Edit"

    override fun getTitleByTransactionType(type: TransactionType): Int {
        return if (type == TransactionType.INCOME) {
            R.string.change_income
        } else {
            R.string.change_outgo
        }
    }

    fun showDialog(transaction: Transaction, transactionDelegate: TransactionDelegate) {
        super.showDialog(transaction.type, transactionDelegate)
        setupFields(transaction)
    }

    private fun setupFields(transaction: Transaction) {
        setupValue(transaction)
        setupDate(transaction)
        setupCategory(transaction)
    }

    private fun setupCategory(transaction: Transaction) {
        val categories = context.resources.getStringArray(getCategoryByTransactionType(transaction.type))
        val indexOfCategory = categories.indexOf(transaction.category)
        categorySpinner.setSelection(indexOfCategory, true)
    }

    private fun setupDate(transaction: Transaction) {
        dateEditText.setText(transaction.date.formatDate())
    }

    private fun setupValue(transaction: Transaction) {
        valueEditText.setText(transaction.total.toString())
    }
}