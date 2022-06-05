package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.account.model.Amount
import com.gitlab.bank.domain.account.model.Deposit

data class DepositDTO(
    val amount: Double
)

fun DepositDTO.toDomain(): Deposit {
    return Deposit(of= Amount(this.amount))
}
