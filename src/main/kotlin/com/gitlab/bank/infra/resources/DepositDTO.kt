package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Operation

data class DepositDTO(
    val amount: Double
)

fun DepositDTO.toDomain(): Operation {
    return Operation.deposit(of= Amount(this.amount))
}
