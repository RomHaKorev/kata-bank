package com.gitlab.bank.domain.client.model

import com.gitlab.bank.domain.EntityTest
import com.gitlab.bank.infra.client.persistence.stubs.ALISON
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*






class ClientTest: EntityTest<Client> {

    @Test
    fun `should create a bank client with an id and a given name`() {
        val id = UUID.randomUUID()
        val client = Client(`identified by`= id,
                             named="Grace Slick")

        assertThat(client.id).isEqualTo(id)
        assertThat(client.name).isEqualTo("Grace Slick")
    }

    override fun createEqualEntities(): Pair<Client, Client> {
        val id = UUID.randomUUID()
        return Client(`identified by`= id,
                named="Grace Slick") to Client(`identified by`= id,
                named="GRACE SLICK")
    }

    override fun createNonEqualEntities(): Pair<Client, Client> {
        val id1 = UUID.randomUUID()
        val id2 = UUID.randomUUID()
        return Client(`identified by`= id1,
                named="Grace Slick") to Client(`identified by`= id2,
                named="Grace Slick")
    }
}
