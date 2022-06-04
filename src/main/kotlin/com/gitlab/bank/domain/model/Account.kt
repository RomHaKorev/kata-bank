package com.gitlab.bank.domain.model

class Account {

    private val deposits = mutableListOf<Deposit>()

    val amount: Amount
        get() = deposits.sum()

    fun make(deposit: Deposit) {
        deposits.add(deposit)
    }


    private fun List<Deposit>.sum() = fold(Amount(0.0)) { acc, deposit -> acc + deposit.amount}
}
