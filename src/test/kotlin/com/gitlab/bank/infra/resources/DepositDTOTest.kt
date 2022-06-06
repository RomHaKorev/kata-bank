package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Operation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DepositDTOTest {
    @Test
    fun `should map DTO to domain`() {
        assertThat(
                DepositDTO(12.0).toDomain()
        ).isEqualTo(Operation.deposit(of= Amount(12.0)))
    }
}
