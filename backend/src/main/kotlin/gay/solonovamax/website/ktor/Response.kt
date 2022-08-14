package gay.solonovamax.website.ktor

import gay.solonovamax.website.Server
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.autohead.AutoHeadResponse
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.gzip
import io.ktor.server.plugins.compression.identity
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.partialcontent.PartialContent
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.path
import io.ktor.server.response.respond
import io.ktor.server.thymeleaf.ThymeleafContent
import org.slf4j.kotlin.toplevel.*

private val logger by getLogger()

fun Application.configureResponse() {
    install(CallLogging)
    
    install(Compression) {
        gzip()
        deflate()
        identity()
    }
    install(PartialContent)
    install(AutoHeadResponse)
    install(ContentNegotiation) {
        // json()
    }
    
    install(StatusPages) {
        // exception<Throwable> { call, cause ->
        //     call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        //     logger.info(cause) { "Internal server error" }
        // }
        //
        // status(HttpStatusCode.InternalServerError) { call, statuscode ->
        //     call.respondText(text = "500:", status = HttpStatusCode.InternalServerError)
        //     logger.info { "Internal server error" }
        // }
        //
        //
        // exception<NotImplementedError> { call, cause ->
        //     call.respond(HttpStatusCode.NotImplemented, cause.message ?: "")
        // }
    
        status(HttpStatusCode.NotFound, HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest,
               HttpStatusCode.Forbidden, HttpStatusCode.RequestTimeout) { call, status ->
            // call.response.status(status)
        
            val variablesMap = mapOf(
                    "config" to Server.config,
                    "path" to call.request.path()
                                    )
        
            call.respond(status, ThymeleafContent("/status/error${status.value}.html", variablesMap))
            // call.respond(baseDir, "/error${status.value}.html")
        }
        
        // statusFile(HttpStatusCode.NotFound, HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest,
        //            HttpStatusCode.Forbidden, HttpStatusCode.RequestTimeout,
        //            filePattern = "public/static/error#.html")
    }
}
