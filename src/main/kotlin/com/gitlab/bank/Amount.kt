package com.gitlab.bank

data class Amount(val value: Double) {
    init {
        assert(value >= 0.0) { "an amount cannot be negative" }
    }
    operator fun plus(other: Amount): Amount {
        return Amount(value + other.value)
    }
}
