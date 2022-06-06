package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.*

enum class OperationType {
    Deposit, Withdrawal
}
data class PastOperationDTO(val type: OperationType, val balance: Double)

fun PastOperation.toDTO(): PastOperationDTO {

    val type = if(operation is Deposit) {
        OperationType.Deposit
    } else {
        OperationType.Withdrawal
    }
    return PastOperationDTO(type, this.balance.toDTO()
    )
}

fun Amount.toDTO(): Double {
    return this.value
}

