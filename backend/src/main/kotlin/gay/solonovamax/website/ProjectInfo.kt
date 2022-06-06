package gay.solonovamax.website

import gay.solonovamax.website.Project.Language
import gay.solonovamax.website.config.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import org.kohsuke.github.GitHubBuilder
import org.slf4j.kotlin.*
import java.awt.Color
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.round

class ProjectInfo(
    config: Config
                 ) {
    private val github = GitHubBuilder().apply {
        withOAuthToken(config.githubToken)
    }.build()
    
    private val logger by getLogger()
    
    lateinit var projects: List<Project> //= projectInfo()
    
    private suspend fun projectInfo(): List<Project> = Server.config.projects.asFlow().map { project ->
        val ghRepo = withContext(Dispatchers.IO) {
            logger.debug { "Fetching project info for ${project.name}" }
            github.getRepository(project.repo)
        }
        
        val languages = ghRepo.listLanguages()
        
        val languageTotalBytes = languages.map { it.value }.sum().toDouble()
        
        val mappedLanguages = languages.mapValues { (_, oldValue) ->
            round(oldValue / languageTotalBytes * 1000) / 10
        }.filterValues { it > 0 && it <= 100 }.map { Language(it.key, it.value) }.takeLast(3)
        
        val lastUpdated = ghRepo.updatedAt.toInstant().atZone(ZoneId.systemDefault())
        val lastUpdatedStringDate = if (lastUpdated.year != ZonedDateTime.now().year)
            "${lastUpdated.month} ${lastUpdated.dayOfMonth}, ${lastUpdated.year}"
        else
            "${lastUpdated.month} ${lastUpdated.dayOfMonth}"
        
        
        Project(name = project.name, url = ghRepo.htmlUrl.toString(), description = ghRepo.description ?: "",
                languages = mappedLanguages, stars = ghRepo.stargazersCount, forks = ghRepo.forksCount, lastUpdated = lastUpdatedStringDate)
    }.toList()
    
    suspend fun refreshProjects() {
        projects = projectInfo()
    }
    
    companion object {
        const val color = "BLUE"
        @JvmField
        val SIZE = Color(1234)
    }
}
