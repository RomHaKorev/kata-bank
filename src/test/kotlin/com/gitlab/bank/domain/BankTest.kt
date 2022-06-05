package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.*
import com.gitlab.bank.domain.spi.BankAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class InMemoryBankAccounts: BankAccounts {
    val accounts = mutableListOf<Account>()

    override fun of(client: BankClient) = accounts.first { it.owner == client }
    override fun commit(account: Account) {
        accounts.removeIf { it == account }
        accounts.add(account)
    }

    fun create(client: BankClient) {
        accounts.add(Account(ownedBy = client))
    }

}

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
