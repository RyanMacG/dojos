package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Presenter
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Request

interface ViewCompletedOneToOnes: AsynchronousUseCase<Request, Presenter> {
    data class Request(val id: Int)

    interface Presenter {
        fun presentOneToOnes(oneToOnes: List<OneToOne>)
        data class OneToOne(
                val id: Int,
                val date: String,
                val facilitator: String,
                val feedbackee: String,
                val completed: Boolean
        )
    }
}
