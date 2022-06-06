package com.gitlab.bank.infra.operation.rest.resources

import com.gitlab.bank.domain.operation.model.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PastOperationDTOTest {
    @Test
    fun `should map a domain deposit to DTO`() {
        Assertions.assertThat(
                PastOperation(Operation.deposit(of=Amount(50.0)), balance = Amount(100.0)).toDTO()
        ).isEqualTo(PastOperationDTO(OperationType.Deposit, 100.0, 50.0, "1975-02-17"))
    }

    @Test
    fun `should map a domain withdrawal to DTO`() {
        Assertions.assertThat(
                PastOperation(Operation.withdrawal(of=Amount(50.0)), balance = Amount(100.0)).toDTO()
        ).isEqualTo(PastOperationDTO(OperationType.Withdrawal, 100.0, -50.0, "1975-02-17"))
    }
}
