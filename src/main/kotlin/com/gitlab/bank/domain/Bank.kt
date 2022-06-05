package com.gitlab.bank.domain

import com.gitlab.bank.domain.api.MakeADeposit
import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.model.Deposit
import com.gitlab.bank.domain.spi.BankAccounts

class Bank(private val bankAccounts: BankAccounts): MakeADeposit {
    override operator fun invoke(client: BankClient, deposit: Deposit) {
        val account = bankAccounts.of(client)

        val accountAfterDeposit = account.make(deposit)

        bankAccounts.commit(accountAfterDeposit)
    }
}
