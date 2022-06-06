package com.gitlab.bank.domain.operation.model

class Withdrawal(of: Amount): Operation {
    val amount = of
    override fun plus(amount: Amount): Amount {
        return amount - this.amount
    }

    override fun equals(other: Any?): Boolean {
        return other is Withdrawal && this.amount == other.amount
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }
}
