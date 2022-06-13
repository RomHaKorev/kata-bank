package com.gitlab.bank.infra.security

import com.gitlab.bank.domain.model.Client
import java.util.*

interface CheckAuthorizationBeforeAction {
    operator fun <T> invoke(clientId: UUID, action: (client: Client) -> T, fallback:  (id: UUID) -> T)
}