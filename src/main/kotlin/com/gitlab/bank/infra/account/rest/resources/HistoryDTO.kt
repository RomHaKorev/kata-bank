package com.gitlab.bank.infra.account.rest.resources

import com.gitlab.bank.domain.account.model.History


data class HistoryDTO(val listing: List<PastOperationDTO>)

fun History.toDTO(): HistoryDTO {
    return HistoryDTO(this.listing.map { it.toDTO() })
}

