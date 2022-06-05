package com.gitlab.bank.domain.model

class Deposit(of: Amount) {
    val amount = of

    override fun equals(other: Any?): Boolean {
        return other is Deposit && this.amount == other.amount
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }
}
