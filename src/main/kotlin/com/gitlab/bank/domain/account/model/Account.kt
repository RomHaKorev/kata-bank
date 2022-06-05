package com.gitlab.bank.domain.account.model

import com.gitlab.bank.domain.client.model.Client

class Account private constructor(val owner: Client,
                                  private val deposits: List<Operation>) {


    constructor(ownedBy: Client): this(ownedBy, emptyList())

    val amount: Amount
        get() = deposits.sum()

    fun make(deposit: Deposit): Account {
        return Account(owner, deposits + listOf(deposit))
    }

    fun make(deposit: Withdrawal): Account {
        return Account(owner, deposits + listOf(deposit))
    }

    override fun equals(other: Any?): Boolean {
        return other is Account && this.owner == other.owner
    }
    override fun hashCode(): Int {
        return owner.hashCode()
    }


    private fun List<Operation>.sum() = fold(Amount(0.0)) { acc, deposit -> deposit + acc}
}
