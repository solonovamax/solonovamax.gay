package gay.solonovamax.website.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val domain: String,
    val repo: String,
    val githubToken: String,
    val projects: List<ProjectConfig>,
    val skills: List<Skill>,
    val links: Links,
                 )
