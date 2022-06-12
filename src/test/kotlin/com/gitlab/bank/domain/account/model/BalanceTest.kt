package com.gitlab.bank.domain.account.model

import com.gitlab.bank.domain.utils.ValueObjectTest

class BalanceTest: ValueObjectTest<Balance> {
    override fun createValue() = Balance(50.0)
    override fun createOtherValue() = Balance(-35.0)
}
