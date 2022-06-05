package com.gitlab.bank.infra

import com.gitlab.bank.domain.account.api.MakeADeposit
import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.http.Context
import java.util.*

class DepositController(val makeDeposit: MakeADeposit,
                        val clients: Clients
) {

    fun makeDeposit(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
        { client ->
            val deposit = ctx.bodyAsClass<com.gitlab.bank.infra.resources.DepositDTO>().toDomain()
            makeDeposit(client, deposit)
            ctx.status(200)
        })
        {
            ctx.status(401)
        }
    }
}
