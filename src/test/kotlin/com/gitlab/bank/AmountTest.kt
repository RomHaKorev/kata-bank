package com.gitlab.bank

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


data class Amount(val value: Double)

class AmountTest {
    @Test
    fun `an amount should be a value object`() {
        assertThat(Amount(12.0)).isEqualTo(Amount(12.0))
    }
}
