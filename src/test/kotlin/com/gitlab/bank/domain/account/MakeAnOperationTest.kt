package com.gitlab.bank.domain.account

import com.gitlab.bank.domain.account.model.*
import com.gitlab.bank.domain.utils.aDate
import com.gitlab.bank.infra.client.persistence.GRACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MakeAnOperationTest {
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
}

