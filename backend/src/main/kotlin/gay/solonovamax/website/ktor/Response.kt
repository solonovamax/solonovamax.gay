package gay.solonovamax.website.ktor

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
import io.ktor.server.response.respondFile
import org.slf4j.kotlin.toplevel.*
import java.io.File

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
        val baseDir = File("public/static/")
        status(HttpStatusCode.NotFound, HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest,
               HttpStatusCode.Forbidden, HttpStatusCode.RequestTimeout) { call, status ->
            call.response.status(status)
            call.respondFile(baseDir, "/error${status.value}.html")
        }
        
        // statusFile(HttpStatusCode.NotFound, HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest,
        //            HttpStatusCode.Forbidden, HttpStatusCode.RequestTimeout,
        //            filePattern = "public/static/error#.html")
    }
}
