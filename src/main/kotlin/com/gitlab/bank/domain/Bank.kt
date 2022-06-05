package com.gitlab.bank.domain

import com.gitlab.bank.domain.account.api.MakeADeposit
import com.gitlab.bank.domain.account.api.MakeAWithdrawal
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.Deposit
import com.gitlab.bank.domain.account.model.Withdrawal
import com.gitlab.bank.domain.account.spi.Accounts

class Bank(private val accounts: Accounts): MakeADeposit, MakeAWithdrawal {
    override operator fun invoke(client: Client, deposit: Deposit) {
        val account = accounts.of(client)
        val accountAfterDeposit = account.make(deposit)
        accounts.commit(accountAfterDeposit)
    }

    override operator fun invoke(client: Client, withdrawal: Withdrawal) {
        val account = accounts.of(client)
        val accountAfterDeposit = account.make(withdrawal)
        accounts.commit(accountAfterDeposit)
    }
}
