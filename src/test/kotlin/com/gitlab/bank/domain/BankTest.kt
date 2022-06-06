package com.gitlab.bank.domain

import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import com.gitlab.bank.infra.operation.persistence.stubs.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)
val anotherDate = LocalDateTime.of(1979, 7, 27, 12, 7, 0)
val thenAnotherDate = LocalDateTime.of(1980, 7, 25, 12, 7, 0)

class BankTest {

    @Test
    fun `a client should make a deposit on their account`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)
        val bank = Bank(bankAccounts)

        bank(GRACE, Operation.deposit(of = Amount(120.0), at=aDate))

        assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Balance(120.0))
    }

    @Test
    fun `a client should make a withdrawal on their account`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE, initialSold=1000.0)
        val bank = Bank(bankAccounts)

        bank(GRACE, Operation.withdrawal(of = Amount(200.0), at = aDate))

        assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Balance(800.0))
    }


    @Test
    fun `a new client should have an empty history of their operations`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        val history = bank(GRACE)

        assertThat(history.isEmpty).isTrue
    }

    @Test
    fun `a client should have an history of all their operations`() {
        val bankAccounts = InMemoryAccounts()
        bankAccounts.create(GRACE)

        val bank = Bank(bankAccounts)

        bank(GRACE, Operation.deposit(of = Amount(120.0), at=aDate))
        bank(GRACE, Operation.deposit(of = Amount(80.0), at=anotherDate))
        bank(GRACE, Operation.withdrawal(of = Amount(50.0), at=thenAnotherDate))

        val history = bank(GRACE)

        assertThat(history).isEqualTo(
                History().`client made`(Operation.deposit(of = Amount(120.0), at=aDate))
                         .`client made`(Operation.deposit(of = Amount(80.0), at=anotherDate))
                         .`client made`(Operation.withdrawal(of = Amount(50.0), at=thenAnotherDate))
        )
    }
}
