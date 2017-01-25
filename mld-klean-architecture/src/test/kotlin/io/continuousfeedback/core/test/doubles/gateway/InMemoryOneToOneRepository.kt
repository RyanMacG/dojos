package io.continuousfeedback.core.test.doubles.gateway

import io.continuousfeedback.core.OneToOneGateway
import io.continuousfeedback.core.domain.OneToOne

class InMemoryOneToOneRepository: OneToOneGateway {
    var findResult: OneToOne? = null

    override fun save(oneToOne: OneToOne) {
        findResult = oneToOne
    }

    override fun find(id: Int): OneToOne? {
        return findResult
    }

    override fun clear() {
        findResult = null
    }
}




