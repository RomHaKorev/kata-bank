package com.gitlab.bank.infra

import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.domain.operation.stubs.InMemoryAccounts
import com.gitlab.bank.infra.resources.toDTO
import com.gitlab.bank.infra.stubs.InMemoryClients
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ListingControllerTest {

    private val accounts = InMemoryAccounts()
    private val app = Application(accounts, InMemoryClients()).app

    @Test
    fun `Should get the history`() = JavalinTest.test(app) { _, client ->
        val account = accounts.create(GRACE).make(Deposit(of=Amount(100.0)))
                                            .make(Withdrawal(of=Amount(50.0)))
                                            .make(Deposit(of=Amount(100.0)))
        accounts.commit(account)

        val response = client.get("/history/${GRACE.id}")
        assertThat(response.code).isEqualTo(200)

        assertThat(response.body?.string()).isEqualTo(
                JavalinJackson().toJsonString(History().`client made`(Deposit(of=Amount(100.0)))
                         .`client made`(Withdrawal(of=Amount(50.0)))
                         .`client made`(Deposit(of=Amount(100.0))).toDTO())
        )

    }
}
