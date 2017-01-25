package io.continuousfeedback.core.test.acceptance

import com.winterbe.expekt.should
import io.continuousfeedback.core.boundary.ContinuousFeedback
import io.continuousfeedback.core.domain.OneToOne
import io.continuousfeedback.core.test.doubles.InMemoryContinuousFeedback
import io.continuousfeedback.core.test.doubles.presenter.ViewCompletedOneToOnesPresenter
import io.continuousfeedback.core.test.doubles.presenter.ViewNextOneToOnePresenter
import io.continuousfeedback.core.usecase.CreateTeamMember
import io.continuousfeedback.core.usecase.ViewCompletedOneToOnes
import io.continuousfeedback.core.usecase.ViewNextOneToOne

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object ViewCompletedOneToOnesSpec : Spek({
    val continuousFeedback: ContinuousFeedback = InMemoryContinuousFeedback()

    describe("viewing a list of completed one to ones") {
        var completedOneToOnes: List<ViewCompletedOneToOnes.Presenter.OneToOne> = listOf()

        fun executeViewCompletedOneToOnes(teamMemberId: Int) {
            val presenter = ViewCompletedOneToOnesPresenter(
                    onSuccess = { completedOneToOnes = it }
            )

            continuousFeedback.executeUseCase(
                    ViewCompletedOneToOnes::class,
                    ViewCompletedOneToOnes.Request(id = teamMemberId),
                    presenter
            )
        }

        given("A team member") {
            context("With an upcoming one to one") {
                beforeEachTest {
                    completedOneToOnes = listOf()
                    continuousFeedback.oneToOneGateway.clear()

                    continuousFeedback.oneToOneGateway.save(OneToOne(
                            id = 1,
                            date = "12/12/2018",
                            facilitator = "Luke",
                            feedbackee = "Scott",
                            completed = true
                    ))

                    executeViewCompletedOneToOnes(1)
                }

                it("returns a list of one to ones") {
                    completedOneToOnes.size.should.equal(1)
                }
            }

            context("Without a completed one to one") {
                beforeEachTest {
                    completedOneToOnes = listOf()
                    continuousFeedback.oneToOneGateway.clear()

                    executeViewCompletedOneToOnes(1)
                }

                it("returns no one to ones") {
                    completedOneToOnes.size.should.equal(0)
                }
            }
        }
    }
})



