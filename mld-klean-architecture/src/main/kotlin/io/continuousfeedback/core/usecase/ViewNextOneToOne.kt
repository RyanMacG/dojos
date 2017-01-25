package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Request

interface ViewNextOneToOne : AsynchronousUseCase<Request, Presenter> {
    data class Request(val id: Int)

    interface Presenter {
        fun presentOneToOne(oneToOne: OneToOne)
        data class OneToOne(
                val id: Int,
                val date: String,
                val facilitator: String,
                val feedbackee: String
        )
    }
}
