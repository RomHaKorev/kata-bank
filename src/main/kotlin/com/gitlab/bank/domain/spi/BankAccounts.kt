package com.gitlab.bank.domain.spi

import com.gitlab.bank.domain.model.Account
import com.gitlab.bank.domain.model.BankClient

interface BankAccounts {
    fun of(client: BankClient): Account
    fun commit(account: Account)
}
