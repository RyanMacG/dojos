package io.continuousfeedback.core.test.acceptance

import com.winterbe.expekt.should
import io.continuousfeedback.core.boundary.ContinuousFeedback
import io.continuousfeedback.core.domain.OneToOne
import io.continuousfeedback.core.test.doubles.InMemoryContinuousFeedback
import io.continuousfeedback.core.test.doubles.presenter.ViewNextOneToOnePresenter
import io.continuousfeedback.core.usecase.CreateTeamMember
import io.continuousfeedback.core.usecase.ViewNextOneToOne

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object ViewNextOneToOneSpec : Spek({
    val continuousFeedback: ContinuousFeedback = InMemoryContinuousFeedback()

    describe("viewing the next one to one") {
        var nextOneToOne: ViewNextOneToOne.Presenter.OneToOne? = null

        fun executeViewNextOneToOne(id: Int) {
            val presenter = ViewNextOneToOnePresenter(
                    onSuccess = { nextOneToOne = it }
            )

            continuousFeedback.executeUseCase(
                    ViewNextOneToOne::class,
                    ViewNextOneToOne.Request(id),
                    presenter
            )
        }

        given("A team member") {
            context("With an upcoming one to one") {
                beforeEachTest {
                    nextOneToOne = null
                    continuousFeedback.oneToOneGateway.clear()

                    continuousFeedback.oneToOneGateway.save(OneToOne(
                            id = 1,
                            date = "12/12/2018",
                            facilitator = "Luke",
                            feedbackee = "Scott"
                    ))

                    executeViewNextOneToOne(1)
                }

                it("returns the one to one") {
                    nextOneToOne?.id.should.equal(1)
                }
            }

            context("Without an upcoming one to one") {
                beforeEachTest {
                    nextOneToOne = null
                    continuousFeedback.oneToOneGateway.clear()

                    executeViewNextOneToOne(1)
                }

                it("returns the one to one") {
                    nextOneToOne.should.be.`null`
                }
            }
        }
    }
})



