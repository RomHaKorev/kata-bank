package com.gitlab.bank.infra

import com.gitlab.bank.domain.operation.api.MakeADeposit
import com.gitlab.bank.domain.operation.api.MakeAWithdrawal
import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.infra.resources.DepositDTO
import com.gitlab.bank.infra.resources.WithdrawalDTO
import com.gitlab.bank.infra.resources.toDTO
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.http.Context
import io.javalin.plugin.json.JavalinJackson
import java.util.*

class ListingController(val getHistoryOf: GetHistoryOf,
                        private val clients: Clients
) {

    fun historyHandler(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
                { client ->
                    ctx.result(JavalinJackson().toJsonString(getHistoryOf(client).toDTO()))
                    ctx.status(200)
                })
        {
            ctx.status(401)
        }
    }
}
