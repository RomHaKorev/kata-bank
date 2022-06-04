package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.model.Deposit

class Bank(val bankAccounts: com.gitlab.bank.domain.BankAccounts) {
    operator fun invoke(client: BankClient, deposit: Deposit) {
        val account = bankAccounts.of(client)

        val accountAfterDeposit = account.make(deposit)

        bankAccounts.commit(accountAfterDeposit)
    }
}
