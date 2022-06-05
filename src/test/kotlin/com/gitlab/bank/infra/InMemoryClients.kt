package com.gitlab.bank.infra

import com.gitlab.bank.domain.model.ALISON
import com.gitlab.bank.domain.model.BankClient
import com.gitlab.bank.domain.model.GRACE
import com.gitlab.bank.domain.spi.BankClients
import java.util.*

class InMemoryClients: BankClients {

    val clients = listOf(GRACE, ALISON)

    override fun findBy(id: UUID): BankClient {
        return clients.first { it.id == id }
    }
}
