package com.gitlab.bank.infra.security

import com.gitlab.bank.domain.doubles.ClientRepositoryFake
import com.gitlab.bank.infra.persistence.GRACE
import com.gitlab.bank.infra.security.CheckClientExistenceService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CheckClientExistenceServiceTest {

    @Test
    fun `should pass if the client exist`() {
        val clients = ClientRepositoryFake()
        clients.register(GRACE)

        val processIfClientExists = CheckClientExistenceService(clients)

        var actionCalled = false
        var fallbackCalled = false

        processIfClientExists(GRACE.id, action = { actionCalled = true }, fallback = { fallbackCalled = true })

        assertThat(actionCalled).isTrue
        assertThat(fallbackCalled).isFalse
    }

    @Test
    fun `should fallback if the client does NOT exist`() {
        val clients = ClientRepositoryFake()

        val processIfClientExists = CheckClientExistenceService(clients)

        var actionCalled = false
        var fallbackCalled = false

        processIfClientExists(GRACE.id, action = { actionCalled = true }, fallback = { fallbackCalled = true })

        assertThat(fallbackCalled).isTrue
        assertThat(actionCalled).isFalse
    }
}