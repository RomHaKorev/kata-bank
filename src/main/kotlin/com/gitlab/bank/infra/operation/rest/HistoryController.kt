package com.gitlab.bank.infra.operation.rest

import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.domain.operation.model.Operation
import com.gitlab.bank.infra.operation.rest.resources.toDTO
import io.javalin.http.Context
import io.javalin.plugin.json.JavalinJackson
import java.util.*

class HistoryController(val getHistoryOf: GetHistoryOf,
                        private val clients: Clients
) {

    fun historyHandler(ctx: Context) = processIfClientExists(ctx)

    private fun processIfClientExists(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
                /* action */
                { client ->
                    val history = getHistoryOf(client)
                    val body = JavalinJackson().toJsonString(history.toDTO())
                    ctx.result(body)
                },
                /* emptyAction */
                {
                    ctx.status(401)
                }
        )
    }
}
