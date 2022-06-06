package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.*

data class PastOperationDTO(val type: OperationType, val balance: Double)

fun PastOperation.toDTO(): PastOperationDTO {
    return PastOperationDTO(operation.type, this.balance.toDTO()
    )
}

fun Amount.toDTO(): Double {
    return this.value
}

