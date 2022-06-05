package com.gitlab.bank.domain.account.model

class Withdrawal(of: Amount): Operation {
    val amount = of
    override fun plus(amount: Amount): Amount {
        return amount - this.amount
    }
}
