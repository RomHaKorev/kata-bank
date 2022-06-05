package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.account.model.Amount
import com.gitlab.bank.domain.account.model.Withdrawal

data class WithdrawalDTO(val amount: Double)

fun WithdrawalDTO.toDomain(): Withdrawal {
    return Withdrawal(of= Amount(this.amount))
}
