package com.gitlab.bank.domain.account.api

import com.gitlab.bank.domain.account.model.Withdrawal
import com.gitlab.bank.domain.client.model.Client

interface MakeAWithdrawal {
    operator fun invoke(client: Client, withdrawal: Withdrawal)
}
