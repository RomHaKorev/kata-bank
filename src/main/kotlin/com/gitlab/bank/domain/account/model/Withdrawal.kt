package com.gitlab.bank.domain.account.model

class Withdrawal(of: Amount): Operation {
    val amount = of
    override fun plus(amount: Amount): Amount {
        TODO("need substraction on Amount")
        //return amount - this.amount
    }
}
