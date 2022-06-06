package com.gitlab.bank.infra

import com.gitlab.bank.domain.Bank
import com.gitlab.bank.domain.operation.spi.Accounts
import com.gitlab.bank.domain.client.spi.Clients
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder

class Application(private val accounts: Accounts, private val bankClients: Clients) {
    val app = Javalin.create().routes {
        val bank = Bank(accounts)
        val operationController = OperationController(bank, bank, bankClients)
        val listingController = ListingController(bank, bankClients)
        ApiBuilder.post("/deposit/{client-id}", operationController::depositHandler)
        ApiBuilder.post("/withdrawal/{client-id}", operationController::withdrawalHandler)
        ApiBuilder.get("/history/{client-id}", listingController::historyHandler)
    }
}
