package com.gitlab.bank.domain.client

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.client.spi.ClientRepository
import java.util.*

class ClientRepositoryFake: ClientRepository {
    private val clients = mutableListOf<Client>()


    fun register(newClient: Client) {
        clients.add(newClient)
    }

    override fun findBy(id: UUID): Optional<Client> {
        return Optional.of(clients.find { it.id == id } ?: return Optional.empty())
    }

}