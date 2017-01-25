package io.continuousfeedback.core.test.doubles.presenter

import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes.Presenter

class ViewCompletedOneToOnesPresenter(val onSuccess: (List<Presenter.OneToOne>) -> Unit) : Presenter {
    override fun presentOneToOnes(oneToOnes: List<Presenter.OneToOne>) =  onSuccess(oneToOnes)
}