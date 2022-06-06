package com.gitlab.bank.domain.client.model

import java.util.*

class Client(`identified by`: UUID, named: String) {
    val id = `identified by`
    val name = named

    override fun equals(other: Any?): Boolean {
        return other is Client && this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
