package io.continuousfeedback.core

import io.continuousfeedback.core.domain.OneToOne

interface OneToOneGateway {
    fun find(id: Int): OneToOne?
    fun save(oneToOne: OneToOne)
    fun clear()
}
