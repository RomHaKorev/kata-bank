package com.gitlab.bank.infra

import com.gitlab.bank.domain.api.MakeADeposit
import com.gitlab.bank.domain.spi.BankClients
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.http.Context
import java.util.*

class DepositController(val makeDeposit: MakeADeposit,
                        val clients: BankClients
) {

    fun makeDeposit(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        val client = clients.findBy(clientId)
        val deposit = ctx.bodyAsClass<com.gitlab.bank.infra.resources.DepositDTO>().toDomain()

        makeDeposit(client, deposit)
    }
}
