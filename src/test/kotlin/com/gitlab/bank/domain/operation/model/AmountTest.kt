package com.gitlab.bank.domain.operation.model

import com.gitlab.bank.domain.ValueObjectTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.AssertionError


class AmountTest: ValueObjectTest<Amount> {


    @Test
    fun `an amount should be summed to another`() {
        assertThat(Amount(12.0) + Amount(16.0)).isEqualTo(Amount(28.0))
    }

    @Test
    fun `an amount should be substracted to another`() {
        assertThat(Amount(20.0) - Amount(16.0)).isEqualTo(Amount(4.0))
    }


    @Test
    fun `an amount should always be positive`() {
        assertThrows<AssertionError> {
            Amount(-100.0)
        }
    }

    override fun createValue() = Amount(20.5)
    override fun createOtherValue() = Amount(12.0)
}
