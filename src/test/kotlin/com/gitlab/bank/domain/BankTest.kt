package com.gitlab.bank.domain

import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.domain.operation.stubs.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BankTest {
    @Test
    fun `a client should make a deposit on their account`() {
        val bankAccounts = InMemoryAccounts()

        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        bank(GRACE, Deposit(of = Amount(120.0)))

        assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Amount(120.0))
    }

    @Test
    fun `a client should make a withdrawal on their account`() {
        val bankAccounts = InMemoryAccounts()

        bankAccounts.create(GRACE, initialSold=1000.0)

        val bank = Bank(bankAccounts)

        bank(GRACE, Withdrawal(of = Amount(200.0)))

        assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Amount(800.0))
    }


    @Test
    fun `a new client should an empty history of their operation`() {
        /*val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        val history = bank.`get history of`(GRACE)

        assertThat(history).isEqualTo(History.EMPTY)*/

        TODO("need history")
    }
}
