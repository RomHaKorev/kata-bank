package com.gitlab.bank.infra.operation.rest.resources

import com.gitlab.bank.domain.operation.model.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class PastOperationDTO(val type: OperationType, val balance: Double, val amount: Double, val date: String)

fun PastOperation.toDTO(): PastOperationDTO {
    return PastOperationDTO(type = operation.type,
                            balance = this.balance.value,
                            amount = when(operation.type) {
                                            OperationType.Deposit -> this.operation.amount.value
                                            OperationType.Withdrawal -> - this.operation.amount.value
                                     },
                            date = this.operation.effectiveDate.toDTO()
                            )
}


private fun LocalDateTime.toDTO(): String {
    return this.format(DateTimeFormatter.ISO_DATE)
}
