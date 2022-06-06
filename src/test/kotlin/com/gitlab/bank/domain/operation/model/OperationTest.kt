package com.gitlab.bank.domain.operation.model

import com.gitlab.bank.domain.ValueObjectTest
import com.gitlab.bank.domain.aDate
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperationTest: ValueObjectTest<Operation> {
    @Test
    fun `create a deposit of a given amount`() {
        val deposit = Operation.deposit(of = Amount(12.0))
        assertThat(
                deposit.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `create a withdrawal of a given amount`() {
        val withdrawal = Operation.withdrawal(of = Amount(12.0))
        assertThat(
                withdrawal.amount
        ).isEqualTo(Amount(12.0))
    }

    @Test
    fun `create an operation at the given date`() {
        val withdrawal = Operation.withdrawal(of = Amount(12.0), at=aDate)
        assertThat(
                withdrawal.effectiveDate
        ).isEqualTo(aDate)
    }

    override fun createValue() = Operation.deposit(of=Amount(12.0))
    override fun createOtherValue() = Operation.deposit(of=Amount(63.0))
}
