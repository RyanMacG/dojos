package io.continuousfeedback.core.test.doubles.presenter

import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter

class ViewNextOneToOnePresenter(val onSuccess: (Presenter.OneToOne) -> Unit) : Presenter {
    override fun presentOneToOne(oneToOne: Presenter.OneToOne) =  onSuccess(oneToOne)
}