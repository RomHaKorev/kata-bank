package com.gitlab.bank.infra.stubs

import com.gitlab.bank.domain.account.model.ALISON
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.account.model.GRACE
import com.gitlab.bank.domain.client.spi.Clients
import java.util.*

class InMemoryClients: Clients {

    val clients = listOf(GRACE, ALISON)

    override fun findBy(id: UUID): Client {
        return clients.first { it.id == id }
    }
}
