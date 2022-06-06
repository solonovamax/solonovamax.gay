package gay.solonovamax.website.thymeleaf

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import org.thymeleaf.IEngineConfiguration
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver
import org.thymeleaf.templateresource.ITemplateResource

class MarkdownTemplateResolver(
    val parser: Parser,
    val renderer: HtmlRenderer
                              ) : AbstractConfigurableTemplateResolver() {
    override fun computeTemplateResource(
        configuration: IEngineConfiguration?,
        ownerTemplate: String?,
        template: String?,
        resourceName: String,
        characterEncoding: String,
        templateResolutionAttributes: MutableMap<String, Any>?
                                        ): ITemplateResource {
        return MarkdownTemplateResource(resourceName, characterEncoding, this)
    }
}
