package gay.solonovamax.website.ktor

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataHolder
import gay.solonovamax.website.thymeleaf.MarkdownTemplateResolver
import gay.solonovamax.website.util.flexmarkOptions
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.config.tryGetString
import io.ktor.server.thymeleaf.Thymeleaf
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import nz.net.ultraq.thymeleaf.layoutdialect.decorators.strategies.GroupingStrategy
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templateresolver.FileTemplateResolver

lateinit var templateEngine: TemplateEngine

fun Application.configureThymeleaf() {
    val development = environment.config.config("ktor").tryGetString("development").toBoolean()
    
    install(Thymeleaf) {
        templateEngine = this
        // setTemplateResolver(ClassLoaderTemplateResolver().apply {
        //     prefix = "templates/"
        //     suffix = ".html"
        //     characterEncoding = "UTF-8"
        // })
        if (development)
            cacheManager = null
        
        addTemplateResolver(FileTemplateResolver().apply {
            prefix = "public/"
            suffix = ".html"
            characterEncoding = "UTF-8"
            order = 2
            if (development)
                isCacheable = false
        })
        
        addTemplateResolver(markdownTemplateResolver(flexmarkOptions(emojiPath = "/assets/img/emoji/"), development))
        // Custom template resolver here
        // addTemplateResolver()
        addDialect(LayoutDialect(GroupingStrategy()))
    }
}


fun markdownTemplateResolver(flexmarkOptions: DataHolder, development: Boolean): MarkdownTemplateResolver {
    return MarkdownTemplateResolver(
            Parser.builder(flexmarkOptions).build(),
            HtmlRenderer.builder(flexmarkOptions)
                    .escapeHtml(false)
                    .indentSize(0)
                    .percentEncodeUrls(true)
                    .build(),
                                   ).apply {
        prefix = "files/"
        suffix = ".md"
        characterEncoding = "UTF-8"
        order = 1
        isCacheable = !development
        
        resolvablePatternSpec.addPattern("/markdown/*")
    }
}
