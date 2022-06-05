package com.gitlab.bank.domain.spi

import com.gitlab.bank.domain.model.BankClient
import java.util.*

interface BankClients {
    fun findBy(id: UUID): BankClient
}
