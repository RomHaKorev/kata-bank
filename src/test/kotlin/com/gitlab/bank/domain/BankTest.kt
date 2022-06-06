package com.gitlab.bank.domain

import com.gitlab.bank.domain.client.model.Client
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
    fun `a new client should have an empty history of their operations`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        val history = bank(GRACE)

        assertThat(history.isEmpty).isTrue
    }

    @Test
    fun `a client should have an history of all their operations`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        bank(GRACE, Deposit(of = Amount(120.0)))
        bank(GRACE, Deposit(of = Amount(80.0)))
        bank(GRACE, Withdrawal(of = Amount(50.0)))

        val history = bank(GRACE)

        assertThat(history).isEqualTo(
                History().`client made`(Deposit(of = Amount(120.0)))
                         .`client made`(Deposit(of = Amount(80.0)))
                         .`client made`(Withdrawal(of = Amount(50.0)))
        )
    }
}
