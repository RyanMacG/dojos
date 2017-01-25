package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.ViewOutstandingFeedback
import io.continuousfeedback.core.domain.Feedback
import io.continuousfeedback.core.test.doubles.gateway.InMemoryFeedbackGateway
import io.continuousfeedback.core.usecase.ViewOutstandingFeedback.Presenter
import io.continuousfeedback.core.usecase.ViewOutstandingFeedback.Presenter.OutstandingFeedback
import io.continuousfeedback.core.usecase.ViewOutstandingFeedback.Request
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek

class ViewOutstandingFeedbackSpec : SubjectSpek<ViewOutstandingFeedback>({
    val feedbackGateway = InMemoryFeedbackGateway()
    subject { ViewOutstandingFeedback(feedbackGateway) }

    given("no feedback for user") {
        var feedback: List<OutstandingFeedback> = listOf()
        beforeGroup {
            subject.execute(Request(teamMemberId = 1), object : Presenter {
                override fun presentFeedback(f: List<OutstandingFeedback>) {
                    feedback = f
                }

            })
        }
        it("should return no feedback") {
            feedback.size.should.be.equal(0)
        }
    }
    given("one item of feedback for user") {
        var feedback: List<OutstandingFeedback> = listOf()
        val expectedComment = "test feedback"
        beforeGroup {
            feedbackGateway.addFeedback(Feedback(1, expectedComment))
            subject.execute(Request(teamMemberId = 1), object : Presenter {
                override fun presentFeedback(f: List<OutstandingFeedback>) {
                    feedback = f
                }

            })
        }
        it("should return single item of feedback") {
            feedback.size.should.be.equal(1)
            feedback[0].comment.should.equal(expectedComment)
        }
    }
    given("multiple items of feedback for user") {
        var feedback : List<OutstandingFeedback> = listOf()
        val expectedComments : List<String> = listOf("Test 1", "Test 2", "Test 3")
        beforeGroup {
            feedbackGateway.clearFeedback()
            expectedComments.forEach { feedbackGateway.addFeedback(Feedback(1, it)) }
            subject.execute(Request(teamMemberId = 1), object : Presenter {
                override fun presentFeedback(f: List<OutstandingFeedback>) {
                    feedback = f;
                }

            })
        }
        it("should return multiple items of feedback") {
            feedback.size.should.be.equal(expectedComments.size)
            feedback.forEachIndexed { i, outstandingFeedback -> expectedComments[i].should.be.equal(outstandingFeedback.comment) }
        }
    }

})