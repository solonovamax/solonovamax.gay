package gay.solonovamax.website.ktor

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.Routing

fun Application.configureServer() {
    // install(Locations)
    install(Routing)
}
