package com.gitlab.bank.domain.operation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DepositTest {
    @Test
    fun `create a deposit of a given amount`() {
        val deposit = Operation.deposit(of = Amount(12.0))
        assertThat(
                deposit.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `Deposit should be a value object`() {
        assertThat(Operation.deposit(of=Amount(12.0))).isEqualTo(Operation.deposit(of=Amount(12.0)))
        assertThat(Operation.deposit(of=Amount(63.0))).isNotEqualTo(Operation.deposit(of=Amount(12.0)))

        assertThat(Operation.deposit(of=Amount(12.0))).isEqualTo(Operation.deposit(of=Amount(12.0)))
        assertThat(Operation.deposit(of=Amount(63.0)).hashCode()).isNotEqualTo(Operation.deposit(of=Amount(12.0)).hashCode())
    }
}
