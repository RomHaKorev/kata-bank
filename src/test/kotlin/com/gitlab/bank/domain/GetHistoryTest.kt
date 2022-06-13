package com.gitlab.bank.domain

import com.gitlab.bank.domain.model.Amount
import com.gitlab.bank.domain.model.Balance
import com.gitlab.bank.domain.model.History
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.domain.utils.aDate
import com.gitlab.bank.domain.utils.anotherDate
import com.gitlab.bank.domain.utils.`given an account`
import com.gitlab.bank.domain.utils.thenAnotherDate
import com.gitlab.bank.infra.persistence.GRACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class GetHistoryOfTest {
    @Test
    fun `a client should make a deposit on their account`() {
        `given an account`(`owned by`=GRACE)

        .`when making`(Operation.deposit(of = Amount(120.0), at=aDate))

        .then {
            assertThat(bankAccounts.of(GRACE).balance).isEqualTo(Balance(120.0))
        }
    }

    @Test
    fun `a client should make a withdrawal on their account`() {
        `given an account`(`owned by`=GRACE, `with the initial sold` = 1000.0)

        .`when making`(Operation.withdrawal(of = Amount(200.0), at = aDate))

        .then {
            assertThat(bankAccounts.of(GRACE).balance).isEqualTo(Balance(800.0))
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