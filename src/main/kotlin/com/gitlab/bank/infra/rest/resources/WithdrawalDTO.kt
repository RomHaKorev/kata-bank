package com.gitlab.bank.infra.rest.resources

import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.Operation
import java.time.LocalDateTime

data class WithdrawalDTO(val amount: Double)

fun WithdrawalDTO.toDomain(effectiveDate: LocalDateTime): Operation {
    return Operation.withdrawal(of= Amount(this.amount), effectiveDate)
}
