package com.gitlab.bank.domain.account.api

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.Operation

interface MakeAnOperation {
    operator fun invoke(client: Client, operation: Operation)
}
