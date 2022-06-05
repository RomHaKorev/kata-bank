package com.gitlab.bank.infra.resources

import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.Deposit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class DepositDTO(
    val amount: Double
)

fun DepositDTO.toDomain(): Deposit {
    return Deposit(of=Amount(this.amount))
}

class DepositDTOTest {
    @Test
    fun `should map DTO to domain`() {
        assertThat(
                DepositDTO(12.0).toDomain()
        ).isEqualTo(Deposit(of= Amount(12.0)))
    }
}
