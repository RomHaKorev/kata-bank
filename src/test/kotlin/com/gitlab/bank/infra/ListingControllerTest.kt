package com.gitlab.bank.infra

import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.infra.stubs.InMemoryAccounts
import com.gitlab.bank.infra.resources.toDTO
import com.gitlab.bank.infra.stubs.GRACE
import com.gitlab.bank.infra.stubs.InMemoryClients
import com.gitlab.bank.infra.stubs.KAREN
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


object Clock {
    private var dateIndex = 0
    val dates = listOf(
            LocalDateTime.of(1975, 2, 17, 12, 7, 0),
            LocalDateTime.of(1979, 7, 27, 12, 7, 0),
            LocalDateTime.of(1980, 7, 25, 12, 7, 0))
    operator fun invoke(): LocalDateTime {
        return dates[dateIndex++ % 3]
    }
}

class ListingControllerTest {

    private val accounts = InMemoryAccounts()
    private val app = Application(accounts, InMemoryClients(), {Clock()}).runner

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
