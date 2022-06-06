package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.History
import com.gitlab.bank.domain.operation.model.PastOperation


data class HistoryDTO(val listing: List<PastOperationDTO>)

fun History.toDTO(): HistoryDTO {
    return HistoryDTO(this.listing.map { it.toDTO() })
}

