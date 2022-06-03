package com.gitlab.bank

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Account {

    private val deposits = mutableListOf<Deposit>()

    val amount: Amount
        get() = deposits.fold(Amount(0.0)) { acc, deposit -> Amount(acc.value + deposit.amount.value)}

    fun make(deposit: Deposit) {
        deposits.add(deposit)
    }
}

class AccountTest {

    @Test
    fun `a new account should be empty`() {
        val account = Account()
        assertThat(account.amount).isEqualTo(Amount(0.0))
    }

    @Test
    fun `a deposit should increase the amount of an account`() {
        val account = Account()

        account.make(Deposit(of= Amount(12.0)))

        assertThat(account.amount).isEqualTo(Amount(12.0))
    }
}
