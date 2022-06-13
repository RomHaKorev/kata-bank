package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.Account
import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.domain.model.History
import com.gitlab.bank.domain.spi.AccountRepository

class HistoryRequestService(val accounts: AccountRepository): GetHistoryOf {
    override fun invoke(client: Client): History {
        return accounts.of(client).buildHistory()
    }

    private fun Account.buildHistory(): History {
        return operations.fold(History()) {
                history, operation -> history.`client made`(operation)
        }
    }
}