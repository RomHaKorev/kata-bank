package com.gitlab.bank.infra

import com.gitlab.bank.domain.HistoryRequestService
import com.gitlab.bank.domain.OperationProcessingService
import com.gitlab.bank.domain.spi.AccountRepository
import com.gitlab.bank.domain.spi.ClientRepository
import com.gitlab.bank.infra.rest.HistoryController
import com.gitlab.bank.infra.rest.OperationController
import com.gitlab.bank.infra.persistence.InMemoryAccountRepository
import com.gitlab.bank.infra.persistence.InMemoryClientRepository
import com.gitlab.bank.infra.security.CheckClientExistenceService
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import java.time.LocalDateTime

class Application(private val accounts: AccountRepository, private val bankClients: ClientRepository, now: () -> LocalDateTime) {
    val runner: Javalin = Javalin.create().routes {
        val makeAnOperation = OperationProcessingService(accounts)
        val getHistoryOf = HistoryRequestService(accounts)

        val checkAuthorizationBeforeAction = CheckClientExistenceService(bankClients)

        val operationController = OperationController(makeAnOperation, checkAuthorizationBeforeAction, now)
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
