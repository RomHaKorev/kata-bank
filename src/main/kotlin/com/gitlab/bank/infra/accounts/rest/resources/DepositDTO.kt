package com.gitlab.bank.infra.accounts.rest.resources

import com.gitlab.bank.domain.account.model.Amount
import com.gitlab.bank.domain.account.model.Operation
import java.time.LocalDateTime

data class DepositDTO(
    val amount: Double
)

fun DepositDTO.toDomain(effectiveDate: LocalDateTime): Operation {
    return Operation.deposit(of= Amount(this.amount), effectiveDate)
}
