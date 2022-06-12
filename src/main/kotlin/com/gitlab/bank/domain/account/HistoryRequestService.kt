package com.gitlab.bank.domain.account

import com.gitlab.bank.domain.account.model.Account
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.domain.account.model.History
import com.gitlab.bank.domain.operation.spi.AccountRepository

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