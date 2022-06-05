package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.account.model.Amount
import com.gitlab.bank.domain.account.model.Deposit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DepositDTOTest {
    @Test
    fun `should map DTO to domain`() {
        assertThat(
                DepositDTO(12.0).toDomain()
        ).isEqualTo(Deposit(of= Amount(12.0)))
    }
}
