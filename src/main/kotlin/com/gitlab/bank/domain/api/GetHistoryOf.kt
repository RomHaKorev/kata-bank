package com.gitlab.bank.domain.operation.api

import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.model.History

interface GetHistoryOf {
    operator fun invoke(client: Client): History
}
