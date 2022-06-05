package com.gitlab.bank.infra

import io.javalin.Javalin
import io.javalin.testtools.JavalinTest
import org.junit.jupiter.api.Test


class Application() {
    val app = Javalin.create().routes {
    }
}

class DepositControllerTest {

    private val app = Application().app

    @Test
    fun `Should make a deposit`() = JavalinTest.test(app) { server, client ->
        /*val response = client.post("/deposit/${GRACE.id}", "")
        assertThat(response.code).isEqualTo(200)*/

        TODO("Need to identify a client by ID")
    }
}
