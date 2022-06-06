package com.gitlab.bank.domain.operation.model

data class History private constructor(val listing: List<PastOperation>) {
    constructor(): this(emptyList())
    fun `client made`(operation: Operation): History {
        val lastOperation = PastOperation(operation, operation + (listing.lastOrNull()?.balance ?: Amount(0.0)))
        return History(listing + lastOperation)
    }

    val isEmpty: Boolean = listing.isEmpty()


}
