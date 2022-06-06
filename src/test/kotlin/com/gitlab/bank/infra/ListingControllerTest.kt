package com.gitlab.bank.infra

import com.gitlab.bank.domain.operation.model.Amount
import com.gitlab.bank.domain.operation.model.Deposit
import com.gitlab.bank.domain.operation.model.GRACE
import com.gitlab.bank.domain.operation.model.Withdrawal
import com.gitlab.bank.domain.operation.stubs.InMemoryAccounts
import com.gitlab.bank.infra.stubs.InMemoryClients
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions
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
        Assertions.assertThat(response.code).isEqualTo(200)
    }
}
