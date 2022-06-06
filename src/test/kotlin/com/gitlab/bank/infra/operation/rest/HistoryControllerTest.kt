package com.gitlab.bank.infra.operation.rest

import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.infra.Application
import com.gitlab.bank.infra.operation.persistence.stubs.InMemoryAccounts
import com.gitlab.bank.infra.operation.rest.resources.toDTO
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import com.gitlab.bank.infra.client.persistence.stubs.InMemoryClients
import com.gitlab.bank.infra.client.persistence.stubs.KAREN
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class HistoryControllerTest {

    private val accounts = InMemoryAccounts()
    private val app = Application(accounts, InMemoryClients(), { ClockFake() }).runner

    @Test
    fun `Should get the history`() = JavalinTest.test(app) { _, client ->
        val account = accounts.create(GRACE).make(Operation.deposit(of= Amount(100.0)))
                                            .make(Operation.withdrawal(of=Amount(50.0)))
                                            .make(Operation.deposit(of=Amount(100.0)))
        accounts.commit(account)

        val response = client.get("/history/${GRACE.id}")
        assertThat(response.code).isEqualTo(200)

        assertThat(response.body?.string()).isEqualTo(
                JavalinJackson().toJsonString(
                        History().`client made`(Operation.deposit(of=Amount(100.0)))
                         .`client made`(Operation.withdrawal(of=Amount(50.0)))
                         .`client made`(Operation.deposit(of=Amount(100.0))).toDTO())
        )

    }

    @Test
    fun `an unknown user cannot get an history`() = JavalinTest.test(app) { _, client ->
        val response = client.get("/history/${KAREN.id}")
        assertThat(response.code).isEqualTo(401)
    }
}
