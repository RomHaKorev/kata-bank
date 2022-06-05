package com.gitlab.bank.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


val GRACE = BankClient(named="Grace Slick")
val ALISON = BankClient(named="Alison Mosshart")

class BankClientTest {

    @Test
    fun `should create a bank client with a given name`() {
        val client = BankClient(named="Grace Slick")

        assertThat(client.name).isEqualTo("Grace Slick")
    }

    @Test
    fun `a bank client should be identified by their name`() {
        assertThat(GRACE).isEqualTo(GRACE)
        assertThat(GRACE).isNotEqualTo(ALISON)

        assertThat(GRACE.hashCode()).isEqualTo(GRACE.hashCode())
        assertThat(GRACE.hashCode()).isNotEqualTo(ALISON.hashCode())
    }
}
