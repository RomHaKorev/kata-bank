package com.gitlab.bank.domain

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.api.MakeADeposit
import com.gitlab.bank.domain.operation.api.MakeAWithdrawal
import com.gitlab.bank.domain.operation.model.Deposit
import com.gitlab.bank.domain.operation.model.History
import com.gitlab.bank.domain.operation.model.Withdrawal
import com.gitlab.bank.domain.operation.spi.Accounts

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

    fun `get history of`(client: Client): History {
        return accounts.of(client).operations.fold(History()) {
            history, operation -> history.`client made`(operation)
        }
    }
}
