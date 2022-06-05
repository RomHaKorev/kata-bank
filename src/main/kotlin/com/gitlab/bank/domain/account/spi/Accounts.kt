package com.gitlab.bank.domain.account.spi

import com.gitlab.bank.domain.account.model.Account
import com.gitlab.bank.domain.client.model.Client

interface Accounts {
    fun of(client: Client): Account
    fun commit(account: Account)
}
