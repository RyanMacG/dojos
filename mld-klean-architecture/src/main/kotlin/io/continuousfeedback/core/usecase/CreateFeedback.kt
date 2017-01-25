package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase

interface CreateFeedback : AsynchronousUseCase<CreateFeedback.Request, CreateFeedback.Presenter> {
    data class Request (val teamMemberID: Int, val comment: String)
    interface Presenter {
        fun onSuccess()
        fun onError() : Error
    }
    enum class Error {EMPTY_COMMENT}
}