package com.gitlab.bank.domain.operation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DepositTest {
    @Test
    fun `create a deposit of a given amount`() {
        val deposit = Deposit(of = Amount(12.0))
        assertThat(
                deposit.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `Deposit should be a value object`() {
        assertThat(Deposit(of=Amount(12.0))).isEqualTo(Deposit(of=Amount(12.0)))
        assertThat(Deposit(of=Amount(63.0))).isNotEqualTo(Deposit(of=Amount(12.0)))

        assertThat(Deposit(of=Amount(12.0))).isEqualTo(Deposit(of=Amount(12.0)))
        assertThat(Deposit(of=Amount(63.0)).hashCode()).isNotEqualTo(Deposit(of=Amount(12.0)).hashCode())
    }
}
