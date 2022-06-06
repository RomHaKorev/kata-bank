package com.gitlab.bank.domain.operation.api

import com.gitlab.bank.domain.operation.model.Withdrawal
import com.gitlab.bank.domain.client.model.Client

interface MakeAWithdrawal {
    operator fun invoke(client: Client, withdrawal: Withdrawal)
}
