package com.gitlab.bank.domain.operation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


data class PastOperation(val operation: Operation, val balance: Amount)

class History private constructor(val listing: List<PastOperation>) {
    constructor(): this(emptyList())
    fun `client made`(operation: Operation): History {
        val lastOperation = PastOperation(operation, operation + (listing.lastOrNull()?.balance ?: Amount(0.0)))
        return History(listing + lastOperation)
    }

    val isEmpty: Boolean = listing.isEmpty()


}

class HistoryTest {

    @Test
    fun `an new history should be empty`() {
        assertThat(History().isEmpty).isTrue
    }

    @Test
    fun `an operation should be added to an history`() {
        val history = History().`client made`(Deposit(of= Amount(20.0)))
        assertThat(history.isEmpty).isFalse
    }

    @Test
    fun `the balance should take into account the operation`() {
        val history = History()
            .`client made`(Deposit(of= Amount(20.0)))
            .`client made`(Deposit(of= Amount(20.0)))
            .`client made`(Withdrawal(of= Amount(10.0)))
        assertThat(history.listing).isEqualTo(
                listOf(
                        PastOperation(Deposit(of= Amount(20.0)), balance = Amount(20.0)),
                        PastOperation(Deposit(of= Amount(20.0)), balance = Amount(40.0)),
                        PastOperation(Withdrawal(of= Amount(10.0)), balance = Amount(30.0))
                )
        )
    }
}
