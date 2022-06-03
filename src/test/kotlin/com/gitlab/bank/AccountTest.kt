package com.gitlab.bank

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Account {
    val amount = Amount(0.0)
}

class AccountTest {

    @Test
    fun `a new account should be empty`() {
        val account = Account()
        assertThat(account.amount).isEqualTo(Amount(0.0))
    }
}
