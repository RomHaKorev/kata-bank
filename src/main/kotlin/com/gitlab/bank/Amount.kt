package com.gitlab.bank

data class Amount(val value: Double) {
    operator fun plus(other: Amount): Amount {
        return Amount(value + other.value)
    }
}
