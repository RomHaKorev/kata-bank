package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.History


data class HistoryDTO(val listing: List<PastOperationDTO>)

fun History.toDTO(): HistoryDTO {
    return HistoryDTO(this.listing.map { it.toDTO() })
}

