package io.continuousfeedback.core

import io.continuousfeedback.core.usecase.ViewNextOneToOne
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter.OneToOne
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Request

class ViewNextOneToOne(val oneToOneGateway: OneToOneGateway) : ViewNextOneToOne {

    override fun execute(request: Request, presenter: Presenter) {
        val id = request.id
        val oneToOne = oneToOneGateway.find(id = id)

        if (oneToOne != null) {
            presenter.presentOneToOne(OneToOne(
                    id = oneToOne.id,
                    date = oneToOne.date,
                    facilitator = oneToOne.facilitator,
                    feedbackee = oneToOne.feedbackee
            ))
        }
    }
}
