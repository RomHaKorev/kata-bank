package com.gitlab.bank.infra.account.persistence

import com.gitlab.bank.domain.account.model.Account
import com.gitlab.bank.domain.account.model.Amount
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.Operation
import com.gitlab.bank.domain.operation.spi.AccountRepository
import java.time.LocalDateTime

class InMemoryAccountRepository: AccountRepository {
    val accounts = mutableListOf<Account>()

    val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)

    override fun of(client: Client): Account {
        return accounts.first { it.owner == client }
    }

    override fun commit(account: Account) {
        accounts.removeIf { it == account }
        accounts.add(account)
    }

    fun create(client: Client, initialSold: Double=0.0): Account {
        val account = Account(ownedBy = client)
        if (initialSold != 0.0) {
            val newAccount = account.make(Operation.deposit(of = Amount(initialSold), at = aDate))
            accounts.add(newAccount)
            return newAccount
        }
        accounts.add(account)
        return account
    }

}
