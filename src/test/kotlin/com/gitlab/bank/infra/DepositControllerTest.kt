package com.gitlab.bank.infra

import com.gitlab.bank.domain.Bank
import com.gitlab.bank.domain.InMemoryBankAccounts
import com.gitlab.bank.domain.api.MakeADeposit
import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.GRACE
import com.gitlab.bank.domain.spi.BankAccounts
import com.gitlab.bank.domain.spi.BankClients
import com.gitlab.bank.infra.resources.DepositDTO
import com.gitlab.bank.infra.resources.DepositDTOTest
import com.gitlab.bank.infra.resources.toDomain
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.http.Context
import io.javalin.plugin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*




class DepositController(val makeDeposit: MakeADeposit,
                        val clients: BankClients) {

    fun makeDeposit(ctx: Context) {
        val clientId = UUID.fromString(ctx.pathParam("client-id"))
        val client = clients.findBy(clientId)
        val deposit = ctx.bodyAsClass<DepositDTO>().toDomain()

        makeDeposit(client, deposit)
    }
}

class Application(val bankAccounts: BankAccounts, val bankClients: BankClients) {
    val app = Javalin.create().routes {
        post("/deposit/{client-id}", DepositController(Bank(bankAccounts), bankClients)::makeDeposit)
    }
}

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
