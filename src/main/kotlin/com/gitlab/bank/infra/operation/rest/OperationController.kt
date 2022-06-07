package com.gitlab.bank.infra.operation.rest

import com.gitlab.bank.domain.client.spi.Clients
import com.gitlab.bank.domain.operation.api.MakeAnOperation
import com.gitlab.bank.domain.operation.model.Operation
import com.gitlab.bank.infra.operation.rest.resources.DepositDTO
import com.gitlab.bank.infra.operation.rest.resources.WithdrawalDTO
import com.gitlab.bank.infra.operation.rest.resources.toDomain
import io.javalin.http.Context
import java.time.LocalDateTime
import java.util.*

class OperationController(val `make an operation`: MakeAnOperation,
                          private val clients: Clients,
                          private val now: () -> LocalDateTime
) {

    fun depositHandler(ctx: Context) {
        val operation = ctx.bodyAsClass<DepositDTO>().toDomain(now())
        processIfClientExists(ctx, operation)
    }

    fun withdrawalHandler(ctx: Context) {
        val operation = ctx.bodyAsClass<WithdrawalDTO>().toDomain(now())
        processIfClientExists(ctx, operation)
    }

    private fun processIfClientExists(ctx: Context, operation: Operation) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        clients.findBy(clientId).ifPresentOrElse(
        /* action */
                { client ->
                    `make an operation`(client, operation)
                    ctx.status(200)
                },
        /* emptyAction */
                {
                    ctx.status(401)
                }
        )
    }
}
