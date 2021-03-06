package com.gitlab.bank.domain.utils

import com.gitlab.bank.domain.HistoryRequestService
import com.gitlab.bank.domain.OperationProcessingService
import com.gitlab.bank.domain.api.MakeAnOperation
import com.gitlab.bank.domain.model.Operation
import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.operation.api.GetHistoryOf
import com.gitlab.bank.infra.persistence.InMemoryAccountRepository
import com.gitlab.bank.infra.persistence.GRACE


fun `given an account`(`owned by`: Client, `with the initial sold`: Double = 0.0): TestHelper {
    return TestHelper(`owned by`, `with the initial sold`)
}



class TestHelper(val client: Client, initialSold: Double) {
    val bankAccounts = InMemoryAccountRepository()

    val `make operation`: MakeAnOperation = OperationProcessingService(bankAccounts)
    val `get history of`: GetHistoryOf = HistoryRequestService(bankAccounts)

    init {
        bankAccounts.create(GRACE, initialSold)
    }

    fun `when making`(vararg operations: Operation): TestHelper {
        operations.forEach { operation -> `make operation`(client, operation) }
        return this
    }

    fun then(assertions: TestHelper.() -> Unit) {
        assertions()
    }
}
