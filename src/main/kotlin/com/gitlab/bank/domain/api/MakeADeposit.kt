package com.gitlab.bank.domain.api

import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.model.Deposit

interface MakeADeposit {
    operator fun invoke(client: BankClient, deposit: Deposit)
}
