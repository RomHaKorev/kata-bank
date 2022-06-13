package com.gitlab.bank.domain

import com.gitlab.bank.domain.api.GetClient
import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.spi.ClientRepository
import java.util.*

class GetClientService(val clients: ClientRepository): GetClient {
    override fun invoke(id: UUID): Optional<Client> {
        return clients.findBy(id)
    }
}