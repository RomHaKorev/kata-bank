package com.gitlab.bank.domain.model

class BankClient(named: String) {
    val name = named

    override fun equals(other: Any?): Boolean {
        return other is BankClient && this.name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
