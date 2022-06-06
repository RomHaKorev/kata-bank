package com.gitlab.bank.domain.operation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class HistoryTest {

    @Test
    fun `an new history should be empty`() {
        assertThat(History().isEmpty).isTrue
    }

    @Test
    fun `an operation should be added to an history`() {
        val history = History().`client made`(Operation.deposit(of= Amount(20.0)))
        assertThat(history.isEmpty).isFalse
    }

    @Test
    fun `the balance should take into account the operation`() {
        val history = History()
            .`client made`(Operation.deposit(of= Amount(20.0)))
            .`client made`(Operation.deposit(of= Amount(20.0)))
            .`client made`(Operation.withdrawal(of= Amount(10.0)))
        assertThat(history.listing).isEqualTo(
                listOf(
                        PastOperation(Operation.deposit(of= Amount(20.0)), balance = Amount(20.0)),
                        PastOperation(Operation.deposit(of= Amount(20.0)), balance = Amount(40.0)),
                        PastOperation(Operation.withdrawal(of= Amount(10.0)), balance = Amount(30.0))
                )
        )
    }
}
