package com.gitlab.bank.infra.rest

import com.gitlab.bank.domain.model.Balance
import com.gitlab.bank.domain.doubles.ClientRepositoryFake
import com.gitlab.bank.infra.Application
import com.gitlab.bank.infra.persistence.InMemoryAccountRepository
import com.gitlab.bank.infra.rest.resources.DepositDTO
import com.gitlab.bank.infra.rest.resources.WithdrawalDTO
import com.gitlab.bank.infra.persistence.GRACE
import com.gitlab.bank.infra.persistence.KAREN
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class OperationControllerTest {
    private val accounts = InMemoryAccountRepository()
    private val clientsFake = ClientRepositoryFake()
    private val app = Application(accounts, clientsFake, { ClockFake() }).runner

    @Test
    fun `Should make a deposit`() = JavalinTest.test(app) { _, client ->
        val deposit = DepositDTO(120.0)

        clientsFake.register(GRACE)
        accounts.create(GRACE)
        val response = client.post("/deposit/${GRACE.id}", JavalinJackson().toJsonString(deposit))
        assertThat(response.code).isEqualTo(200)

        assertThat(accounts.of(GRACE).balance).isEqualTo(Balance(120.0))
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

        clientsFake.register(GRACE)
        accounts.create(GRACE, initialSold = 1000.0)
        val response = client.post("/withdrawal/${GRACE.id}", JavalinJackson().toJsonString(withdrawal))
        assertThat(response.code).isEqualTo(200)

        assertThat(accounts.of(GRACE).balance).isEqualTo(Balance(880.0))
    }

    @Test
    fun `an unknown user cannot make a withdrawal`() = JavalinTest.test(app) { _, client ->
        val withdrawal = WithdrawalDTO(120.0)

        val response = client.post("/withdrawal/${KAREN.id}", JavalinJackson().toJsonString(withdrawal))
        assertThat(response.code).isEqualTo(401)
    }
}
