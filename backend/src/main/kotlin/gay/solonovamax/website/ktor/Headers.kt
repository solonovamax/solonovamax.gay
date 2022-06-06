package gay.solonovamax.website.ktor

import io.ktor.http.CacheControl.MaxAge
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.CachingOptions
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cachingheaders.CachingHeaders
import io.ktor.server.plugins.conditionalheaders.ConditionalHeaders
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import io.ktor.server.plugins.forwardedheaders.ForwardedHeaders

fun Application.configureHeaders() {
    install(ConditionalHeaders)
    install(DefaultHeaders)
    install(ForwardedHeaders)
    
    configureCORSHeaders()
    configureCachingHeaders()
}

fun Application.configureCORSHeaders() {
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        // allowCredentials = true
        allowNonSimpleContentTypes = true
        
        anyHost()
    }
}

fun Application.configureCachingHeaders() {
    install(CachingHeaders) {
        options { _, outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Application.OctetStream -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 30))
                ContentType.Application.FontWoff    -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 180))
                ContentType.Image.Any               -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 180))
                ContentType.Text.CSS                -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 14))
                ContentType.Text.JavaScript         -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 14))
                ContentType.Application.JavaScript  -> CachingOptions(MaxAge(maxAgeSeconds = 60 * 60 * 24 * 14))
                else                                -> null
            }
        }
    }
}
