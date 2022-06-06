package com.gitlab.bank.infra

import com.gitlab.bank.domain.operation.api.MakeADeposit
import com.gitlab.bank.domain.operation.api.MakeAWithdrawal
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.infra.resources.DepositDTO
import com.gitlab.bank.infra.resources.WithdrawalDTO
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.http.Context
import java.util.*

class OperationController(val makeDeposit: MakeADeposit,
                          val makeAWithdrawal: MakeAWithdrawal,
                          private val clients: Clients
) {

    fun depositHandler(ctx: Context) {
        clientOperation(ctx ) { client ->
            val deposit = ctx.bodyAsClass<DepositDTO>().toDomain()
            makeDeposit(client, deposit)
        }
    }

    fun withdrawalHandler(ctx: Context) {
        clientOperation(ctx ) { client ->
                    val withdrawal = ctx.bodyAsClass<WithdrawalDTO>().toDomain()
                    makeAWithdrawal(client, withdrawal)
        }
    }

    private fun clientOperation(ctx: Context, handler: (client: Client) -> Unit) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
                { client ->
                    handler(client)
                    ctx.status(200)
                })
        {
            ctx.status(401)
        }
    }
}
