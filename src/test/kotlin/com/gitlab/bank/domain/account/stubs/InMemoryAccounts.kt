package com.gitlab.bank.domain.account.stubs

import com.gitlab.bank.domain.account.model.Account
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.spi.Accounts

class InMemoryAccounts: Accounts {
    val accounts = mutableListOf<Account>()

    override fun of(client: Client) = accounts.first { it.owner == client }
    override fun commit(account: Account) {
        accounts.removeIf { it == account }
        accounts.add(account)
    }

    fun create(client: Client) {
        accounts.add(Account(ownedBy = client))
    }

}
