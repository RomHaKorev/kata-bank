package com.gitlab.bank.infra.client.persistence

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.client.spi.ClientRepository
import java.util.*

private fun String.id(): UUID = UUID.nameUUIDFromBytes(this.toByteArray())

val KAREN = Client(`identified by` = "Karen O".id(), named="Karen O")
val GRACE = Client(`identified by` = "Grace Slick".id(), named="Grace Slick")
val ALISON = Client(`identified by` = "Alison Mosshart".id(), named="Alison Mosshart")


class InMemoryClientRepository: ClientRepository {

    val clients = listOf(GRACE, ALISON)

    override fun findBy(id: UUID): Optional<Client> {
        return Optional.of(clients.firstOrNull { it.id == id } ?: return Optional.empty())
    }
}
