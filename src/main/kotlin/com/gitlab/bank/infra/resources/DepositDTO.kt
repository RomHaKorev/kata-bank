package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Deposit

data class DepositDTO(
    val amount: Double
)

fun DepositDTO.toDomain(): Deposit {
    return Deposit(of= Amount(this.amount))
}
