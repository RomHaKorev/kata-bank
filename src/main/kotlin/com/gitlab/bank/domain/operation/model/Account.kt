package com.gitlab.bank.domain.operation.model

import com.gitlab.bank.domain.client.model.Client

class Account private constructor(val owner: Client,
                                  val operations: List<Operation>) {


    constructor(ownedBy: Client): this(ownedBy, emptyList())

    val amount: Amount
        get() = operations.sum()

    fun make(deposit: Deposit): Account {
        return Account(owner, operations + listOf(deposit))
    }

    fun make(deposit: Withdrawal): Account {
        return Account(owner, operations + listOf(deposit))
    }

    override fun equals(other: Any?): Boolean {
        return other is Account && this.owner == other.owner
    }
    override fun hashCode(): Int {
        return owner.hashCode()
    }


    private fun List<Operation>.sum() = fold(Amount(0.0)) { acc, deposit -> deposit + acc}
}
