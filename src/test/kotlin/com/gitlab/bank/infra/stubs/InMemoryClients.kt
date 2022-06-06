package com.gitlab.bank.infra.stubs

import com.gitlab.bank.domain.operation.model.ALISON
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.model.GRACE
import com.gitlab.bank.domain.client.spi.Clients
import java.util.*

class InMemoryClients: Clients {

    val clients = listOf(GRACE, ALISON)

    override fun findBy(id: UUID): Optional<Client> {
        return Optional.of(clients.firstOrNull { it.id == id } ?: return Optional.empty())
    }
}
