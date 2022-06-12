package com.gitlab.bank.domain.client.api

import com.gitlab.bank.domain.client.model.Client
import java.util.*

interface GetClient {
    operator fun invoke(id: UUID): Optional<Client>
}