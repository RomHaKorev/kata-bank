package com.gitlab.bank.infra.security

import com.gitlab.bank.domain.model.Client
import com.gitlab.bank.domain.spi.ClientRepository
import java.util.*

class CheckClientExistenceService(val repository: ClientRepository): CheckAuthorizationBeforeAction {
    override operator fun <T> invoke(clientId: UUID, action: (client: Client) -> T, fallback:  (id: UUID) -> T) {
        repository.findBy(clientId).ifPresentOrElse(
            /* action */
            {
                action(it)
            },
            /* emptyAction */
            {
                fallback(clientId)
            })
    }
}