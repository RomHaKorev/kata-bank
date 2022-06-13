package com.gitlab.bank.infra.rest

import com.gitlab.bank.domain.api.MakeAnOperation
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.infra.rest.resources.DepositDTO
import com.gitlab.bank.infra.rest.resources.WithdrawalDTO
import com.gitlab.bank.infra.rest.resources.toDomain
import com.gitlab.bank.infra.security.CheckAuthorizationBeforeAction
import io.javalin.http.Context
import java.time.LocalDateTime
import java.util.*

class OperationController(val `make an operation`: MakeAnOperation,
                          private val checkAuthorizationBeforeAction: CheckAuthorizationBeforeAction,
                          private val now: () -> LocalDateTime
) {
    fun depositHandler(ctx: Context) {
        val operation = ctx.bodyAsClass<DepositDTO>().toDomain(now())
        processOperation(ctx, operation)
    }
    fun withdrawalHandler(ctx: Context) {
        val operation = ctx.bodyAsClass<WithdrawalDTO>().toDomain(now())
        processOperation(ctx, operation)
    }
    private fun processOperation(ctx: Context, operation: Operation) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        checkAuthorizationBeforeAction(
            clientId,
            action = { client ->
                `make an operation`(client, operation)
                ctx.status(200)
            },
            fallback = {
                ctx.status(401)
            }
        )
    }
}
