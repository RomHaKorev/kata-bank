package com.gitlab.bank.domain.operation.model


enum class OperationType {
    Deposit, Withdrawal
}

data class Operation(val type: OperationType, val amount: Amount) {
    operator fun plus(balance: Amount): Amount {
        return when(type) {
            OperationType.Deposit -> balance + amount
            OperationType.Withdrawal -> balance - amount
        }
    }

    companion object {
        fun deposit(of: Amount): Operation {
            return Operation(OperationType.Deposit, of)
        }

        fun withdrawal(of: Amount): Operation {
            return Operation(OperationType.Withdrawal, of)
        }
    }
}
