package com.gitlab.bank.domain.account.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.AssertionError


class AmountTest {
    @Test
    fun `an amount should be a value object`() {
        assertThat(Amount(12.0)).isEqualTo(Amount(12.0))
    }

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
}
