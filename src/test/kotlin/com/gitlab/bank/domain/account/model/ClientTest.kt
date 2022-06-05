package com.gitlab.bank.domain.account.model

import com.gitlab.bank.domain.client.model.Client
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*


private fun String.id(): UUID = UUID.nameUUIDFromBytes(this.toByteArray())

val GRACE = Client(`identified by` = "Grace Slick".id(), named="Grace Slick")
val ALISON = Client(`identified by` = "Alison Mosshart".id(), named="Alison Mosshart")

class ClientTest {

    @Test
    fun `should create a bank client with an id and a given name`() {
        val id = UUID.randomUUID()
        val client = Client(`identified by`= id,
                                named="Grace Slick")

        assertThat(client.id).isEqualTo(id)
        assertThat(client.name).isEqualTo("Grace Slick")
    }

    @Test
    fun `a bank client should be identified by their id`() {
        assertThat(GRACE).isEqualTo(GRACE)
        assertThat(GRACE).isNotEqualTo(ALISON)

        assertThat(GRACE.hashCode()).isEqualTo(GRACE.hashCode())
        assertThat(GRACE.hashCode()).isNotEqualTo(ALISON.hashCode())
    }
}
