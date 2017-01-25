package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.CreateFeedback
import io.continuousfeedback.core.FeedbackGateway
import io.continuousfeedback.core.test.doubles.gateway.InMemoryFeedbackGateway
import io.continuousfeedback.core.usecase.CreateFeedback.Error
import io.continuousfeedback.core.usecase.CreateFeedback.Presenter
import io.continuousfeedback.core.usecase.CreateFeedback.Request
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek

class CreateFeedbackSpec : SubjectSpek<CreateFeedback>({
    val feedbackGateway : InMemoryFeedbackGateway = InMemoryFeedbackGateway()
    subject { CreateFeedback(feedbackGateway) }

    given("Valid feedback") {
        var successful: Boolean? = null
        val expectedTeamMemberID = 1
        val expectedComment = "Test feedback"
        beforeGroup {
            subject.execute(Request(teamMemberID = expectedTeamMemberID, comment = expectedComment), object : Presenter {
                override fun onSuccess() {
                    successful = true
                }

                override fun onError(): Error {
                    return Error.EMPTY_COMMENT
                }
            })
        }
        it("saves the feedback in the gateway") {
            feedbackGateway.getAll().size.should.be.equal(expectedTeamMemberID)
        }
        it("should save with the correct comment and team member id") {
            feedbackGateway.getAll()[0].comment.should.be.equal(expectedComment)
            feedbackGateway.getAll()[0].teamMemberId.should.be.equal(expectedTeamMemberID)
        }
        it("should notify the presenter that it was successful") {
            successful.should.be.`true`
        }
    }
})