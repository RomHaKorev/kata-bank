package com.gitlab.bank.infra

import com.gitlab.bank.domain.account.HistoryRequestService
import com.gitlab.bank.domain.account.OperationProcessingService
import com.gitlab.bank.domain.operation.spi.AccountRepository
import com.gitlab.bank.domain.client.spi.ClientRepository
import com.gitlab.bank.infra.accounts.rest.HistoryController
import com.gitlab.bank.infra.accounts.rest.OperationController
import com.gitlab.bank.infra.accounts.persistence.InMemoryAccountRepository
import com.gitlab.bank.infra.client.persistence.InMemoryClientRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import java.time.LocalDateTime

class Application(private val accounts: AccountRepository, private val bankClients: ClientRepository, now: () -> LocalDateTime) {
    val runner = Javalin.create().routes {
        val makeAnOperation = OperationProcessingService(accounts)
        val getHistoryOf = HistoryRequestService(accounts)
        val operationController = OperationController(makeAnOperation, bankClients, now)
        val historyController = HistoryController(getHistoryOf, bankClients)
        ApiBuilder.post("/deposit/{client-id}", operationController::depositHandler)
        ApiBuilder.post("/withdrawal/{client-id}", operationController::withdrawalHandler)
        ApiBuilder.get("/history/{client-id}", historyController::historyHandler)
    }
}


fun main() {
    val clients = InMemoryClientRepository()
    val accounts = InMemoryAccountRepository()
    clients.clients.forEach {
        accounts.create(it)
    }
    val application = Application(accounts, clients) { LocalDateTime.now() }

    application.runner.start(8080)
}
