package com.gitlab.bank.domain

import com.gitlab.bank.domain.client.model.Client
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.domain.operation.api.MakeAnOperation
import com.gitlab.bank.domain.operation.model.*
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import com.gitlab.bank.infra.operation.persistence.stubs.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime



class BankTest {
    @Test
    fun `a client should make a deposit on their account`() {
        `given an account`(`owned by`=GRACE)

        .`when making`(Operation.deposit(of = Amount(120.0), at=aDate))

        .then {
            assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Balance(120.0))
        }
    }

    @Test
    fun `a client should make a withdrawal on their account`() {
        `given an account`(`owned by`=GRACE, `with the initial sold` = 1000.0)

        .`when making`(Operation.withdrawal(of = Amount(200.0), at = aDate))

        .then {
            assertThat(bankAccounts.of(GRACE).amount).isEqualTo(Balance(800.0))
        }
    }

    @Test
    fun `a new client should have an empty history of their operations`() {
        `given an account`(GRACE)

        .then {
            assertThat(`get history of`(GRACE).isEmpty).isTrue
        }
    }

    @Test
    fun `a client should have an history of all their operations`() {
        `given an account`(GRACE)

        .`when making`(
                Operation.deposit(of = Amount(120.0), at=aDate),
                Operation.deposit(of = Amount(80.0), at=anotherDate),
                Operation.withdrawal(of = Amount(50.0), at=thenAnotherDate)
        )

        .then {
            assertThat(`get history of`(GRACE)).isEqualTo(
                    History()
                        .`client made`(Operation.deposit(of = Amount(120.0), at=aDate))
                        .`client made`(Operation.deposit(of = Amount(80.0), at=anotherDate))
                        .`client made`(Operation.withdrawal(of = Amount(50.0), at=thenAnotherDate))
            )
        }
    }
}



private fun `given an account`(`owned by`: Client, `with the initial sold`: Double = 0.0): TestHelper {
    return TestHelper(`owned by`, `with the initial sold`)
}

val aDate: LocalDateTime = LocalDateTime.of(1975, 2, 17, 12, 7, 0)
val anotherDate = LocalDateTime.of(1979, 7, 27, 12, 7, 0)
val thenAnotherDate = LocalDateTime.of(1980, 7, 25, 12, 7, 0)

class TestHelper(val client: Client, initialSold: Double) {
    val bankAccounts = InMemoryAccounts()
    private val bank = Bank(bankAccounts)

    val `make operation`: MakeAnOperation = bank
    val `get history of`: GetHistoryOf = bank

    init {
        bankAccounts.create(GRACE, initialSold)
    }

    fun `when making`(vararg operations: Operation): TestHelper {
        operations.forEach { operation -> `make operation`(client, operation) }
        return this
    }

    fun then(assertions: TestHelper.() -> Unit) {
        assertions()
    }
}
