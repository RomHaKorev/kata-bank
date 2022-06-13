package com.gitlab.bank.domain.api

import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.model.Operation

interface MakeAnOperation {
    operator fun invoke(client: Client, operation: Operation)
}
