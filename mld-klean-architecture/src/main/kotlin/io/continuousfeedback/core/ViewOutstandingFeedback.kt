package io.continuousfeedback.core

import io.continuousfeedback.core.usecase.ViewOutstandingFeedback

class ViewOutstandingFeedback(val feedbackGateway: FeedbackGateway) : ViewOutstandingFeedback {

    override fun execute(request: ViewOutstandingFeedback.Request, presenter: ViewOutstandingFeedback.Presenter) {
        val feedback = feedbackGateway.getOutstandingFeedbackForMember(request.teamMemberId)
        var comments : MutableList<ViewOutstandingFeedback.Presenter.OutstandingFeedback> = mutableListOf()
        feedback.forEach { comments.add(ViewOutstandingFeedback.Presenter.OutstandingFeedback(comment=it.comment)) }
        presenter.presentFeedback(comments)
    }
}