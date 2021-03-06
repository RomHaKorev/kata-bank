package com.gitlab.bank.infra.rest.resources

import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.infra.rest.resources.DepositDTO
import com.gitlab.bank.infra.rest.resources.toDomain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class DepositDTOTest {
    val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)
    @Test
    fun `should map DTO to domain`() {
        assertThat(
                DepositDTO(12.0).toDomain(aDate)
        ).isEqualTo(Operation.deposit(of= Amount(12.0), at=aDate))
    }
}
