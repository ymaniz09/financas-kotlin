package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.model.TransactionType

class TransactionDialog(viewGroup: ViewGroup,
                        context: Context) : BaseDialog(context, viewGroup) {
    override val positiveButtonTitle: String
        get() = "Add"

    override fun getTitleByTransactionType(type: TransactionType): Int {
        return if (type == TransactionType.INCOME) {
            R.string.add_income
        } else {
            R.string.add_outgo
        }
    }

}