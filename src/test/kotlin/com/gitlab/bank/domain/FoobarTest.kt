package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.Account
import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.model.Deposit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


interface BankAccounts {
    fun of(client: BankClient): Account
    fun commit(account: Account)
}

class MakeADeposit(val bankAccounts: BankAccounts) {
    operator fun invoke(client: BankClient, deposit: Deposit) {
        val account = bankAccounts.of(client)

        val accountAfterDeposit = account.make(deposit)

        bankAccounts.commit(accountAfterDeposit)
    }
}

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

class FoobarTest {
    @Test
    fun `a client should make a deposit on their account`() {
        val bankAccounts = InMemoryBankAccounts()
        val client = BankClient(named="Grace Slick")
        bankAccounts.create(client)

        val makeADeposit = MakeADeposit(bankAccounts)

        makeADeposit(client, Deposit(of = Amount(120.0)))

        assertThat(bankAccounts.of(client).amount).isEqualTo(Amount(120.0))
    }
}
