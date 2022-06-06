package gay.solonovamax.website.config

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val twitter: String,
    val github: String,
    val gitlab: String,
    val discord: String,
    val matrix: String,
    val reddit: String,
    val youtube: String,
    val stackOverflow: String,
    val email: String,
                ) {
}