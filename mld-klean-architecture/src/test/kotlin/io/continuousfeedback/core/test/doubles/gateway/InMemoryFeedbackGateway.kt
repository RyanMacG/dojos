package io.continuousfeedback.core.test.doubles.gateway

import io.continuousfeedback.core.FeedbackGateway
import io.continuousfeedback.core.domain.Feedback

class InMemoryFeedbackGateway : FeedbackGateway {
    var feedback: MutableList<Feedback> = mutableListOf()

    fun addFeedback(f: Feedback) {
        feedback.add(f)
    }

    fun clearFeedback() {
        feedback.clear()
    }

    fun getAll() : List<Feedback> {
        return feedback.toList()
    }

    override fun getOutstandingFeedbackForMember(id: Int): List<Feedback> {
        return feedback.toList()
    }

    override fun createFeedback(feedback: Feedback): Boolean {
        this.feedback.add(feedback)
        return true
    }

}