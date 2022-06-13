package com.gitlab.bank.domain

import com.gitlab.bank.domain.doubles.ClientRepositoryFake
import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.infra.persistence.GRACE
import com.gitlab.bank.infra.persistence.KAREN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GetClientTest {
    @Test
    fun `should retrieve the client from their UUID`() {
        val clients = ClientRepositoryFake()
        val getClient = GetClientService(clients)
        clients.register(GRACE)

        assertThat(
            getClient(GRACE.id)
        ).isEqualTo(
            Optional.of(GRACE)
        )
    }

    @Test
    fun `should get nothing when the uuid does not reference a client`() {
        val clients = ClientRepositoryFake()
        val getClient = GetClientService(clients)

        assertThat(
            getClient(KAREN.id)
        ).isEqualTo(
            Optional.empty<Client>()
        )
    }
}

