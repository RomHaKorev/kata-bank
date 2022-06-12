package com.gitlab.bank.domain.account.model

data class Balance(val value: Double) {
    operator fun plus(other: Amount): Balance {
        return Balance(value + other.value)
    }

    operator fun minus(other: Amount): Balance {
        return Balance(value - other.value)
    }
}
