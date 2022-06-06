package com.gitlab.bank.domain.operation.api

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.model.Operation

interface MakeAnOperation {
    operator fun invoke(client: Client, operation: Operation)
}
