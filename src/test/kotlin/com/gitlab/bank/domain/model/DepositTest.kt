package com.gitlab.bank.domain.model

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
}
