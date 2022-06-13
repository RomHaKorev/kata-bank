package com.gitlab.bank.domain.model

import java.time.LocalDateTime


enum class OperationType {
    Deposit, Withdrawal
}

data class Operation(val type: OperationType, val amount: Amount, val effectiveDate: LocalDateTime) {
    operator fun plus(balance: Balance): Balance {
        return when(type) {
            OperationType.Deposit -> balance + amount
            OperationType.Withdrawal -> balance - amount
        }
    }

    companion object {
        fun deposit(of: Amount, at: LocalDateTime): Operation {
            return Operation(OperationType.Deposit, of, at)
        }

        fun withdrawal(of: Amount, at: LocalDateTime): Operation {
            return Operation(OperationType.Withdrawal, of, at)
        }
    }
}
