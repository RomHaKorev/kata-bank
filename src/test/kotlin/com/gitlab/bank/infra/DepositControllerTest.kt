package com.gitlab.bank.infra

import com.gitlab.bank.domain.InMemoryBankAccounts
import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.GRACE
import com.gitlab.bank.infra.resources.DepositDTO
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DepositControllerTest {

    private val accounts = InMemoryBankAccounts()
    private val app = Application(accounts, InMemoryClients()).app

    @Test
    fun `Should make a deposit`() = JavalinTest.test(app) { server, client ->

        val deposit = DepositDTO(120.0)

        accounts.create(GRACE)
        val response = client.post("/deposit/${GRACE.id}", JavalinJackson().toJsonString(deposit))
        assertThat(response.code).isEqualTo(200)

        assertThat(accounts.of(GRACE).amount).isEqualTo(Amount(120.0))
    }
}
