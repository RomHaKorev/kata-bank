package com.gitlab.bank.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BankClient(named: String) {
    val name = named
}

class BankClientTest {

    @Test
    fun `should create a bank client with a given name`() {
        val client = BankClient(named="Grace Slick")

        assertThat(client.name).isEqualTo("Grace Slick")
    }
}
