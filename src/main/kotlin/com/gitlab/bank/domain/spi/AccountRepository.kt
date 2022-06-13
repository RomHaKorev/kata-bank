package com.gitlab.bank.domain.spi

import com.gitlab.bank.domain.model.Account
import com.gitlab.bank.domain.model.Client

interface AccountRepository {
    fun of(client: Client): Account
    fun commit(account: Account)
}
