package com.gitlab.bank.infra

import com.gitlab.bank.domain.Bank
import com.gitlab.bank.domain.account.spi.Accounts
import com.gitlab.bank.domain.client.spi.Clients
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder

class Application(val accounts: Accounts, val bankClients: Clients) {
    val app = Javalin.create().routes {
        ApiBuilder.post("/deposit/{client-id}", OperationController(Bank(accounts), bankClients)::makeDeposit)
    }
}
