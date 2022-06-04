package com.gitlab.bank.domain.model

class Account private constructor(val owner: BankClient,
                                  private val deposits: List<Deposit>) {


    constructor(ownedBy: BankClient): this(ownedBy, emptyList())

    val amount: Amount
        get() = deposits.sum()

    fun make(deposit: Deposit): Account {
        return Account(owner, deposits + listOf(deposit))
    }

    override fun equals(other: Any?): Boolean {
        return other is Account && this.owner == other.owner
    }
    override fun hashCode(): Int {
        return owner.hashCode()
    }


    private fun List<Deposit>.sum() = fold(Amount(0.0)) { acc, deposit -> acc + deposit.amount}
}
