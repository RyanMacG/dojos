package io.continuousfeedback.core

import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Presenter
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Presenter.OneToOne
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Request

class ViewCompletedOneToOnes(val oneToOneGateway: OneToOneGateway) : ViewCompletedOneToOnes {
    override fun execute(request: Request, presenter: Presenter) {
        val id = request.id
        val oneToOne = oneToOneGateway.find(id = id)

        if (oneToOne != null) {
            presenter.presentOneToOnes(listOf(OneToOne(
                    id = oneToOne.id,
                    date = oneToOne.date,
                    facilitator = oneToOne.facilitator,
                    feedbackee = oneToOne.feedbackee,
                    completed = oneToOne.completed
            )))
        }
    }
}
