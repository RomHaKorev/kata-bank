package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Withdrawal

data class WithdrawalDTO(val amount: Double)

fun WithdrawalDTO.toDomain(): Withdrawal {
    return Withdrawal(of= Amount(this.amount))
}
