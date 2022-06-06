package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Operation

data class WithdrawalDTO(val amount: Double)

fun WithdrawalDTO.toDomain(): Operation {
    return Operation.withdrawal(of= Amount(this.amount))
}
