package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Deposit
import com.gitlab.bank.domain.operation.model.History
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HistoryDTOTest {
    @Test
    fun `should map empty domain to DTO`() {
        Assertions.assertThat(
                History().toDTO()
        ).isEqualTo(HistoryDTO(emptyList()))
    }

    @Test
    fun `should map domain to DTO`() {
        Assertions.assertThat(
                History().`client made`(Deposit(of= Amount(100.0))).toDTO()
        ).isEqualTo(HistoryDTO(listOf(PastOperationDTO(OperationType.Deposit, 100.0))))
    }
}
