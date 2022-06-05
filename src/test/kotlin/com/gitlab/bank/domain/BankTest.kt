package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BankTest {
    @Test
    fun `a client should make a deposit on their account`() {
        val bankAccounts = InMemoryBankAccounts()

        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        bank(GRACE, Deposit(of = Amount(120.0)))

        assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Amount(120.0))
    }
}
