package io.continuousfeedback.core.domain

data class OneToOne(
        val id: Int,
        val date: String,
        val facilitator: String,
        val feedbackee: String
)