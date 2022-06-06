package com.gitlab.bank.infra

import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.infra.resources.toDTO
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
                    val history = getHistoryOf(client)
                    println(history)
                    val body = JavalinJackson().toJsonString(history.toDTO())
                    ctx.result(body)
                    ctx.status(200)
                })
        {
            ctx.status(401)
        }
    }
}
