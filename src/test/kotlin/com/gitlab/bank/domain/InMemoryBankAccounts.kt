package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.Account
import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.spi.BankAccounts

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
