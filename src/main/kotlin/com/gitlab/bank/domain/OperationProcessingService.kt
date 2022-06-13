package com.gitlab.bank.domain

import com.gitlab.bank.domain.api.MakeAnOperation
import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.domain.spi.AccountRepository

class OperationProcessingService(val accounts: AccountRepository): MakeAnOperation {
    override operator fun invoke(client: Client, operation: Operation) {
        val account = accounts.of(client)
        val accountAfterDeposit = account.make(operation)
        accounts.commit(accountAfterDeposit)
    }
}