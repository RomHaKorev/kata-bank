package com.gitlab.bank.domain.account.model

interface Operation {
    operator fun plus(amount: Amount): Amount
}
