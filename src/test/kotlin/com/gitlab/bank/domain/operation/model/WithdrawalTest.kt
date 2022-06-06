package com.gitlab.bank.domain.operation.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class WithdrawalTest {
    @Test
    fun `create a withdrawal of a given amount`() {
        val withdrawal = Operation.withdrawal(of = Amount(12.0))
        Assertions.assertThat(
                withdrawal.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `Withdrawal should be a value object`() {
        Assertions.assertThat(Operation.withdrawal(of = Amount(12.0))).isEqualTo(Operation.withdrawal(of=Amount(12.0)))
        Assertions.assertThat(Operation.withdrawal(of = Amount(63.0))).isNotEqualTo(Operation.withdrawal(of=Amount(12.0)))

        Assertions.assertThat(Operation.withdrawal(of = Amount(12.0))).isEqualTo(Operation.withdrawal(of=Amount(12.0)))
        Assertions.assertThat(Operation.withdrawal(of = Amount(63.0)).hashCode()).isNotEqualTo(Operation.withdrawal(of=Amount(12.0)).hashCode())
    }
}
