package com.gitlab.bank.infra

import com.gitlab.bank.domain.Bank
import com.gitlab.bank.domain.operation.spi.Accounts
import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.infra.stubs.InMemoryAccounts
import com.gitlab.bank.infra.stubs.InMemoryClients
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import java.time.LocalDateTime

class Application(private val accounts: Accounts, private val bankClients: Clients, now: () -> LocalDateTime) {
    val runner = Javalin.create().routes {
        val bank = Bank(accounts)
        val operationController = OperationController(bank, bankClients, now)
        val listingController = ListingController(bank, bankClients)
        ApiBuilder.post("/deposit/{client-id}", operationController::depositHandler)
        ApiBuilder.post("/withdrawal/{client-id}", operationController::withdrawalHandler)
        ApiBuilder.get("/history/{client-id}", listingController::historyHandler)
    }
}


fun main() {
    val clients = InMemoryClients()
    val accounts = InMemoryAccounts()
    clients.clients.forEach {
        accounts.create(it)
        println("${it.name}: ${it.id}")
    }
    val application = Application(accounts, clients) { LocalDateTime.now() }

    application.runner.start(8080)
}
