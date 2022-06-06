package com.gitlab.bank.domain.client.model

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.infra.stubs.ALISON
import com.gitlab.bank.infra.stubs.GRACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*






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
