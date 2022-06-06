package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.History
import com.gitlab.bank.domain.operation.model.Operation
import com.gitlab.bank.domain.operation.model.OperationType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class HistoryDTOTest {
    @Test
    fun `should map empty domain to DTO`() {
        Assertions.assertThat(
                History().toDTO()
        ).isEqualTo(HistoryDTO(emptyList()))
    }

    @Test
    fun `should map domain to DTO`() {
        val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)
        Assertions.assertThat(
                History().`client made`(Operation.deposit(of= Amount(100.0), at=aDate)).toDTO()
        ).isEqualTo(HistoryDTO(listOf(PastOperationDTO(OperationType.Deposit, 100.0))))
    }
}
