package gay.solonovamax.website

import gay.solonovamax.website.config.Config
import gay.solonovamax.website.ktor.configureHeaders
import gay.solonovamax.website.ktor.configureResponse
import gay.solonovamax.website.ktor.configureServer
import gay.solonovamax.website.ktor.configureThymeleaf
import gay.solonovamax.website.util.readYaml
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.CompressedFileType
import io.ktor.server.http.content.files
import io.ktor.server.http.content.preCompressed
import io.ktor.server.http.content.static
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.thymeleaf.ThymeleafContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.kotlin.*
import java.io.File
import java.io.FileInputStream
import java.util.concurrent.TimeUnit


object Server {
    val config = readYaml<Config>(FileInputStream("config.yaml"))
    private val info = ProjectInfo(config)
    
    private val logger by getLogger()
    
    fun Application.main() {
        projectRefresh()
        
        configureHeaders()
        configureResponse()
        configureServer()
        configureThymeleaf()
        
        routing {
            static("assets") {
                preCompressed(CompressedFileType.BROTLI, CompressedFileType.GZIP) {
                    files("public/assets")
                }
            }
            // get("/") {
            //
            // }
            // val root = File(".")
            val webRoot = File("./public/web")
            
            webRoot.walk().forEach { file ->
                if (file.extension != "html")
                    return@forEach
                
                val fileName = file.relativeTo(webRoot).nameWithoutExtension
                get(fileName.removeSuffix("index")) {
                    val variablesMap = mapOf(
                            "config" to config,
                            "projects" to info.projects,
                            "skills" to config.skills,
                            "path" to context.request.path()
                                            )
                    
                    call.respond(ThymeleafContent("web/${fileName}", variablesMap))
                }
            }
        }
    }
    
    private fun Application.projectRefresh() {
        runBlocking {
            info.refreshProjects()
        }
        
        
        launch {
            while (true) {
                delay(TimeUnit.HOURS.toMillis(1))
                
                logger.info { "Refreshing projects..." }
                info.refreshProjects()
            }
        }
    }
}
