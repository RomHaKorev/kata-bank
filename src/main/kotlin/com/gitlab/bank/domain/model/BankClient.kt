package com.gitlab.bank.domain.model

import java.util.*

class BankClient(`identified by`: UUID, named: String) {
    val id = `identified by`
    val name = named

    override fun equals(other: Any?): Boolean {
        return other is BankClient && this.id == other.id
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
