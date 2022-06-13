package com.gitlab.bank.domain.api

import com.gitlab.bank.domain.model.Client
import java.util.*

interface GetClient {
    operator fun invoke(id: UUID): Optional<Client>
}