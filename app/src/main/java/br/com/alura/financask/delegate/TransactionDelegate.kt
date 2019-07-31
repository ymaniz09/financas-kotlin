package br.com.alura.financask.delegate

import br.com.alura.financask.model.Transaction

interface TransactionDelegate {
    fun delegate(transaction: Transaction)
}