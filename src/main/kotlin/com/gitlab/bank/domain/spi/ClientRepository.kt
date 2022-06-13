package com.gitlab.bank.domain.spi

import com.gitlab.bank.domain.model.Client
import java.util.*

interface ClientRepository {
    fun findBy(id: UUID): Optional<Client>
}
