package com.gitlab.bank.domain.client.spi

import com.gitlab.bank.domain.client.model.Client
import java.util.*

interface Clients {
    fun findBy(id: UUID): Optional<Client>
}
