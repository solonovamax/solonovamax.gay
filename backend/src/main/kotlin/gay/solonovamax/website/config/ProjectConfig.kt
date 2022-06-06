package gay.solonovamax.website.config

import kotlinx.serialization.Serializable

@Serializable
data class ProjectConfig(
    val name: String,
    val baseDomain: String,
    val repo: String,
                        )
