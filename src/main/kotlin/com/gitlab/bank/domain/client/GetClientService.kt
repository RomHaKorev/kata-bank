package com.gitlab.bank.domain.client

import com.gitlab.bank.domain.client.api.GetClient
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.client.spi.ClientRepository
import java.util.*

class GetClientService(val clients: ClientRepository): GetClient {
    override fun invoke(id: UUID): Optional<Client> {
        return clients.findBy(id)
    }
}