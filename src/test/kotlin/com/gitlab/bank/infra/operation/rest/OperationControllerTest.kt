package com.gitlab.bank.infra.operation.rest

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.infra.Application
import com.gitlab.bank.infra.operation.rest.resources.DepositDTO
import com.gitlab.bank.infra.operation.rest.resources.WithdrawalDTO
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import com.gitlab.bank.infra.operation.persistence.stubs.InMemoryAccounts
import com.gitlab.bank.infra.client.persistence.stubs.InMemoryClients
import com.gitlab.bank.infra.client.persistence.stubs.KAREN
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class OperationControllerTest {

    private val accounts = InMemoryAccounts()
    private val app = Application(accounts, InMemoryClients(), { ClockFake() }).runner

    @Test
    fun `Should make a deposit`() = JavalinTest.test(app) { _, client ->

        val deposit = DepositDTO(120.0)

        accounts.create(GRACE)
        val response = client.post("/deposit/${GRACE.id}", JavalinJackson().toJsonString(deposit))
        assertThat(response.code).isEqualTo(200)

        assertThat(accounts.of(GRACE).amount).isEqualTo(Amount(120.0))
    }

    @Test
    fun `an unknown user cannot make a deposit`() = JavalinTest.test(app) { _, client ->
        val deposit = DepositDTO(120.0)

        val response = client.post("/deposit/${KAREN.id}", JavalinJackson().toJsonString(deposit))
        assertThat(response.code).isEqualTo(401)
    }

    @Test
    fun `Should make a withdrawal`() = JavalinTest.test(app) { _, client ->
        val withdrawal = WithdrawalDTO(120.0)

        accounts.create(GRACE, initialSold = 1000.0)
        val response = client.post("/withdrawal/${GRACE.id}", JavalinJackson().toJsonString(withdrawal))
        assertThat(response.code).isEqualTo(200)

        assertThat(accounts.of(GRACE).amount).isEqualTo(Amount(880.0))
    }

    @Test
    fun `an unknown user cannot make a withdrawal`() = JavalinTest.test(app) { _, client ->
        val withdrawal = WithdrawalDTO(120.0)

        val response = client.post("/withdrawal/${KAREN.id}", JavalinJackson().toJsonString(withdrawal))
        assertThat(response.code).isEqualTo(401)
    }
}
