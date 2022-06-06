package com.gitlab.bank.domain.operation.stubs

import com.gitlab.bank.domain.operation.model.Account
import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Deposit
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.spi.Accounts

class InMemoryAccounts: Accounts {
    val accounts = mutableListOf<Account>()

    override fun of(client: Client) = accounts.first { it.owner == client }
    override fun commit(account: Account) {
        accounts.removeIf { it == account }
        accounts.add(account)
    }

    fun create(client: Client, initialSold: Double=0.0) {
        val account = Account(ownedBy = client)
        if (initialSold != 0.0) {
            accounts.add(account.make(Deposit(of = Amount(initialSold))))
            return
        }
        accounts.add(account)
    }

}
