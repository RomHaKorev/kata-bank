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
import okhttp3.OkHttp
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

        response.shouldBeOK()
        response.shouldBe(
                History().`client made`(Operation.deposit(of=Amount(100.0)))
                         .`client made`(Operation.withdrawal(of=Amount(50.0)))
                         .`client made`(Operation.deposit(of=Amount(100.0))).toDTO()
        )

    }

    @Test
    fun `an unknown user cannot get an history`() = JavalinTest.test(app) { _, client ->
        val response = client.get("/history/${KAREN.id}")
        response.shouldBeUnAuthorized()
    }
}



fun okhttp3.Response.shouldBeOK() {
    assertThat(this.code).isEqualTo(200)
}

fun okhttp3.Response.shouldBeUnAuthorized() {
    assertThat(this.code).isEqualTo(401)
}

fun okhttp3.Response.shouldBe(expected: Any) {
    assertThat(body?.string()).isEqualTo(
            JavalinJackson().toJsonString(expected)
    )
}
