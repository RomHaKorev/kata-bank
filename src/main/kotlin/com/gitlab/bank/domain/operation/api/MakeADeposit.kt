package com.gitlab.bank.domain.operation.api

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.model.Deposit

interface MakeADeposit {
    operator fun invoke(client: Client, deposit: Deposit)
}
