package io.continuousfeedback.core

import io.continuousfeedback.core.domain.Feedback

interface FeedbackGateway {
    fun getOutstandingFeedbackForMember(id: Int) : List<Feedback>
    fun createFeedback(feedback: Feedback) : Boolean
}