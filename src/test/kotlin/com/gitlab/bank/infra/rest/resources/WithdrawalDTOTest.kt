package com.gitlab.bank.infra.rest.resources

import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.infra.rest.resources.WithdrawalDTO
import com.gitlab.bank.infra.rest.resources.toDomain
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class WithdrawalDTOTest {
    @Test
    fun `should map DTO to domain`() {
        val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)
        Assertions.assertThat(
                WithdrawalDTO(12.0).toDomain(aDate)
        ).isEqualTo(Operation.withdrawal(of= Amount(12.0), at=aDate))
    }
}
