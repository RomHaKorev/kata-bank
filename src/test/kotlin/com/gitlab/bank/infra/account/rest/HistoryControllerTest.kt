package com.gitlab.bank.infra.account.rest

import com.gitlab.bank.domain.account.model.*
import com.gitlab.bank.infra.Application
import com.gitlab.bank.infra.account.persistence.InMemoryAccountRepository
import com.gitlab.bank.infra.account.rest.resources.toDTO
import com.gitlab.bank.infra.client.persistence.GRACE
import com.gitlab.bank.infra.client.persistence.InMemoryClientRepository
import com.gitlab.bank.infra.client.persistence.KAREN
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class HistoryControllerTest {

    private val accounts = InMemoryAccountRepository()
    private val app = Application(accounts, InMemoryClientRepository(), { ClockFake() }).runner

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
