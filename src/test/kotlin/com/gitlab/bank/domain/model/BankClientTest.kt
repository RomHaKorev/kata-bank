package com.gitlab.bank.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BankClient(named: String) {
    val name = named

    override fun equals(other: Any?): Boolean {
        return other is BankClient && this.name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class BankClientTest {

    @Test
    fun `should create a bank client with a given name`() {
        val client = BankClient(named="Grace Slick")

        assertThat(client.name).isEqualTo("Grace Slick")
    }

    @Test
    fun `a bank client should be identified by their name`() {
        assertThat(BankClient(named="Grace Slick")).isEqualTo(BankClient(named="Grace Slick"))
        assertThat(BankClient(named="Grace Slick")).isNotEqualTo(BankClient(named="Alison Mosshart"))

        assertThat(BankClient(named="Grace Slick").hashCode()).isEqualTo(BankClient(named="Grace Slick").hashCode())
        assertThat(BankClient(named="Grace Slick").hashCode()).isNotEqualTo(BankClient(named="Alison Mosshart").hashCode())
    }
}
