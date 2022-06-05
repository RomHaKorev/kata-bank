package com.gitlab.bank.domain.account.api

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.Deposit

interface MakeADeposit {
    operator fun invoke(client: Client, deposit: Deposit)
}
