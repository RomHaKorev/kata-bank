package com.gitlab.bank.domain.account.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class WithdrawalTest {
    @Test
    fun `create a withdrawal of a given amount`() {
        val withdrawal = Withdrawal(of = Amount(12.0))
        Assertions.assertThat(
                withdrawal.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `Withdrawal should be a value object`() {
        Assertions.assertThat(Withdrawal(of = Amount(12.0))).isEqualTo(Withdrawal(of=Amount(12.0)))
        Assertions.assertThat(Withdrawal(of = Amount(63.0))).isNotEqualTo(Withdrawal(of=Amount(12.0)))

        Assertions.assertThat(Withdrawal(of = Amount(12.0))).isEqualTo(Withdrawal(of=Amount(12.0)))
        Assertions.assertThat(Withdrawal(of = Amount(63.0)).hashCode()).isNotEqualTo(Withdrawal(of=Amount(12.0)).hashCode())
    }
}
