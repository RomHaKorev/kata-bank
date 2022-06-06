package com.gitlab.bank.domain.operation.model

interface Operation {
    operator fun plus(amount: Amount): Amount
}
