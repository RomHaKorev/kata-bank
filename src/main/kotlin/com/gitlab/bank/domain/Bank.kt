package com.gitlab.bank.domain

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.domain.operation.api.MakeAnOperation
import com.gitlab.bank.domain.operation.model.History
import com.gitlab.bank.domain.operation.model.Operation
import com.gitlab.bank.domain.operation.spi.Accounts

class Bank(private val accounts: Accounts): MakeAnOperation, GetHistoryOf {
    override operator fun invoke(client: Client, operation: Operation) {
        val account = accounts.of(client)
        val accountAfterDeposit = account.make(operation)
        accounts.commit(accountAfterDeposit)
    }


    override fun invoke(client: Client): History {
        return accounts.of(client).operations.fold(History()) {
            history, operation -> history.`client made`(operation)
        }
    }
}
