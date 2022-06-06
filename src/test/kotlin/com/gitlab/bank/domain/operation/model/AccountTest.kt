package com.gitlab.bank.domain.operation.model

import com.gitlab.bank.domain.EntityTest
import com.gitlab.bank.domain.aDate
import com.gitlab.bank.infra.client.persistence.stubs.ALISON
import com.gitlab.bank.infra.client.persistence.stubs.GRACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun Operation.Companion.deposit(of: Amount): Operation {
    return deposit(of= of, at=aDate)
}

fun Operation.Companion.withdrawal(of: Amount): Operation {
    return withdrawal(of= of, at=aDate)
}

class AccountTest: EntityTest<Account> {
    @Test
    fun `a new account should be empty`() {
        val account = newAccount()
        assertThat(account.amount).isEqualTo(Balance(0.0))
    }

    @Test
    fun `a deposit should increase the amount of an account`() {
        val account = newAccount()

        val accountAfterDeposit = account.make(Operation.deposit(of= Amount(12.0)))

        assertThat(accountAfterDeposit.amount).isEqualTo(Balance(12.0))
    }

    @Test
    fun `a withdrawal should decrease the amount of an account`() {
        val account = newAccount(initialSold=200.0)
        val accountAfterDeposit = account.make(Operation.withdrawal(of= Amount(50.0)))

        assertThat(accountAfterDeposit.amount).isEqualTo(Balance(150.0))
    }

    override fun createEqualEntities(): Pair<Account, Account> {
        return Account(ownedBy= GRACE) to Account(ownedBy= GRACE)
    }

    override fun createNonEqualEntities(): Pair<Account, Account> {
        return Account(ownedBy= GRACE) to Account(ownedBy= ALISON)
    }

    private fun newAccount(initialSold: Double=0.0): Account {
        val account = Account(ownedBy= GRACE)
        if (initialSold != 0.0)
            return account.make(Operation.deposit(of=Amount(initialSold)))
        return account
    }
}

