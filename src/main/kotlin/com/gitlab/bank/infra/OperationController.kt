package com.gitlab.bank.infra

import com.gitlab.bank.domain.account.api.MakeADeposit
import com.gitlab.bank.domain.account.api.MakeAWithdrawal
import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.infra.resources.DepositDTO
import com.gitlab.bank.infra.resources.WithdrawalDTO
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.http.Context
import java.util.*

class OperationController(val makeDeposit: MakeADeposit,
                          val makeAWithdrawal: MakeAWithdrawal,
                          val clients: Clients
) {

    fun makeDeposit(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
        { client ->
            val deposit = ctx.bodyAsClass<DepositDTO>().toDomain()
            makeDeposit(client, deposit)
            ctx.status(200)
        })
        {
            ctx.status(401)
        }
    }

    fun makeWithdrawal(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
                { client ->
                    val withdrawal = ctx.bodyAsClass<WithdrawalDTO>().toDomain()
                    makeAWithdrawal(client, withdrawal)
                    ctx.status(200)
                })
        {
            ctx.status(401)
        }
    }
}
