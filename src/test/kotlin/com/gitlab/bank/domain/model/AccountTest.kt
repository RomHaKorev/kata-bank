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

        val accountAfterDeposit = account.make(Deposit(of= Amount(12.0)))

        assertThat(accountAfterDeposit.amount).isEqualTo(Amount(12.0))
    }

    @Test
    fun `an account should be owned by a single client`() {
        assertThat(Account(ownedBy= BankClient(named="Grace Slick")))
            .isEqualTo(Account(ownedBy= BankClient(named="Grace Slick")))

        assertThat(Account(ownedBy= BankClient(named="Grace Slick")))
            .isNotEqualTo(Account(ownedBy= BankClient(named="Alison Mosshart")))

        assertThat(Account(ownedBy= BankClient(named="Grace Slick")).hashCode())
            .isEqualTo(Account(ownedBy= BankClient(named="Grace Slick")).hashCode())

        assertThat(Account(ownedBy= BankClient(named="Grace Slick")).hashCode())
            .isNotEqualTo(Account(ownedBy= BankClient(named="Alison Mosshart")).hashCode())
    }


    private fun newAccount(): Account {
        return Account(ownedBy= BankClient(named="Grace Slick"))
    }
}
