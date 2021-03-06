package com.gitlab.bank.domain.model

class Account private constructor(val owner: Client,
                                  val operations: List<Operation>) {


    constructor(ownedBy: Client): this(ownedBy, emptyList())

    val balance: Balance
        get() = operations.sum()

    fun make(deposit: Operation): Account {
        return Account(owner, operations + listOf(deposit))
    }

    override fun equals(other: Any?): Boolean {
        return other is Account && this.owner == other.owner
    }
    override fun hashCode(): Int {
        return owner.hashCode()
    }


    private fun List<Operation>.sum() = fold(Balance(0.0)) { acc, deposit -> deposit + acc}
}
