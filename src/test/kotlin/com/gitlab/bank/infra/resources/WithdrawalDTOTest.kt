package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Withdrawal
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class WithdrawalDTOTest {
    @Test
    fun `should map DTO to domain`() {
        Assertions.assertThat(
                WithdrawalDTO(12.0).toDomain()
        ).isEqualTo(Withdrawal(of= Amount(12.0)))
    }
}
