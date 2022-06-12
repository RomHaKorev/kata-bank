package com.gitlab.bank.domain.client

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.infra.client.persistence.GRACE
import com.gitlab.bank.infra.client.persistence.KAREN
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

