package com.gitlab.bank.infra

import com.gitlab.bank.domain.Bank
import com.gitlab.bank.domain.spi.BankAccounts
import com.gitlab.bank.domain.spi.BankClients
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder

class Application(val bankAccounts: BankAccounts, val bankClients: BankClients) {
    val app = Javalin.create().routes {
        ApiBuilder.post("/deposit/{client-id}", DepositController(Bank(bankAccounts), bankClients)::makeDeposit)
    }
}
