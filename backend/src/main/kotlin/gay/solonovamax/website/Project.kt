package gay.solonovamax.website

data class Project(
    val name: String,
    val url: String,
    val description: String,
    val languages: List<Language>,
    val stars: Int,
    val forks: Int,
    val lastUpdated: String,
                  ) {
    
    data class Language(val name: String, val amount: Double)
}
