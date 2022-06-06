package gay.solonovamax.website.config

import kotlinx.serialization.Serializable

@Serializable
data class Skill(val name: String, val icon: String = "", val amount: Int)