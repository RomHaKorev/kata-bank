package com.gitlab.bank.domain.operation.api

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.model.History

interface GetHistoryOf {
    operator fun invoke(client: Client): History
}
