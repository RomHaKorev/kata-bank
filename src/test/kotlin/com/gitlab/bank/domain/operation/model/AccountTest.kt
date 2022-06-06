package com.gitlab.bank.domain.operation.model

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

        val accountAfterDeposit = account.make(Operation.deposit(of= Amount(12.0)))

        assertThat(accountAfterDeposit.amount).isEqualTo(Amount(12.0))
    }

    @Test
    fun `a withdrawal should decrease the amount of an account`() {
        val account = newAccount(initialSold=200.0)
        val accountAfterDeposit = account.make(Operation.withdrawal(of= Amount(50.0)))

        assertThat(accountAfterDeposit.amount).isEqualTo(Amount(150.0))
    }

    @Test
    fun `an account should be owned by a single client`() {
        assertThat(Account(ownedBy= GRACE))
            .isEqualTo(Account(ownedBy= GRACE))

        assertThat(Account(ownedBy= GRACE))
            .isNotEqualTo(Account(ownedBy= ALISON))

        assertThat(Account(ownedBy= GRACE).hashCode())
            .isEqualTo(Account(ownedBy= GRACE).hashCode())

        assertThat(Account(ownedBy= GRACE).hashCode())
            .isNotEqualTo(Account(ownedBy= ALISON).hashCode())
    }


    private fun newAccount(initialSold: Double=0.0): Account {
        val account = Account(ownedBy= GRACE)
        if (initialSold != 0.0)
            return account.make(Operation.deposit(of=Amount(initialSold)))
        return account
    }
}
