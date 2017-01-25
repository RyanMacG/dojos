package io.continuousfeedback.core

import io.continuousfeedback.core.domain.Feedback
import io.continuousfeedback.core.usecase.CreateFeedback

class CreateFeedback(val feedbackGateway: FeedbackGateway) : CreateFeedback {
    override fun execute(request: CreateFeedback.Request, presenter: CreateFeedback.Presenter) {
        if(feedbackGateway.createFeedback(Feedback(request.teamMemberID, request.comment))) {
            presenter.onSuccess()
        }
    }

}