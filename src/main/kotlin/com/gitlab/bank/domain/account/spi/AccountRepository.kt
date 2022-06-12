package com.gitlab.bank.domain.operation.spi

import com.gitlab.bank.domain.account.model.Account
import com.gitlab.bank.domain.client.model.Client

interface AccountRepository {
    fun of(client: Client): Account
    fun commit(account: Account)
}
