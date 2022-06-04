package com.gitlab.bank.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class AccountTest {

    @Test
    fun `a new account should be empty`() {
        val account = newAccount()
        assertThat(account.amount).isEqualTo(Amount(0.0))
    }

    @Test
    fun `a deposit should increase the amount of an account`() {
        val account = newAccount()

        account.make(Deposit(of= Amount(12.0)))

        assertThat(account.amount).isEqualTo(Amount(12.0))
    }


    private fun newAccount(): Account {
        return Account()
    }
}
