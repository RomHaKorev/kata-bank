package com.gitlab.bank.domain.client.spi

import com.gitlab.bank.domain.client.model.Client
import java.util.*

interface ClientRepository {
    fun findBy(id: UUID): Optional<Client>
}
