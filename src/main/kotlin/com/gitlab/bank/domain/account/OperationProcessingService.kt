package com.gitlab.bank.domain.account

import com.gitlab.bank.domain.account.api.MakeAnOperation
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.Operation
import com.gitlab.bank.domain.operation.spi.AccountRepository

class OperationProcessingService(val accounts: AccountRepository): MakeAnOperation {
    override operator fun invoke(client: Client, operation: Operation) {
        val account = accounts.of(client)
        val accountAfterDeposit = account.make(operation)
        accounts.commit(accountAfterDeposit)
    }
}