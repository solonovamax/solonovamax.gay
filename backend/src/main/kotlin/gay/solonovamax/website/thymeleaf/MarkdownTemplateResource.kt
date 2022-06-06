package gay.solonovamax.website.thymeleaf

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import kotlinx.serialization.Transient
import org.slf4j.kotlin.*
import org.thymeleaf.templateresource.ITemplateResource
import org.thymeleaf.util.StringUtils
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.CharArrayReader
import java.io.CharArrayWriter
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class MarkdownTemplateResource(
    path: String,
    private val characterEncoding: String,
    private val markdownTemplateResolver: MarkdownTemplateResolver,
                              ) : ITemplateResource {
    constructor(
        file: File,
        characterEncoding: String,
        markdownTemplateResolver: MarkdownTemplateResolver,
               ) : this(file.path, characterEncoding, markdownTemplateResolver)
    
    private val parser: Parser
        get() = markdownTemplateResolver.parser
    private val renderer: HtmlRenderer
        get() = markdownTemplateResolver.renderer
    
    @Transient
    private val file = File(path).normalize().absoluteFile
    
    override fun getDescription(): String = file.absolutePath
    
    override fun getBaseName(): String = file.nameWithoutExtension
    
    override fun exists(): Boolean = file.exists()
    
    @Throws(IOException::class)
    override fun reader(): Reader {
        val inputStream: InputStream = FileInputStream(file)
        
        val input = if (!StringUtils.isEmptyOrWhitespace(characterEncoding)) {
            BufferedReader(InputStreamReader(BufferedInputStream(inputStream), characterEncoding))
        } else BufferedReader(InputStreamReader(BufferedInputStream(inputStream)))
        
        val document = parser.parseReader(input)
        
        val charWriter = CharArrayWriter()
        
        renderer.render(document, charWriter)
        
        return CharArrayReader(charWriter.toCharArray())
    }
    
    override fun relative(relativeLocation: String): ITemplateResource {
        return MarkdownTemplateResource(file.parentFile.resolve(relativeLocation), characterEncoding, markdownTemplateResolver)
    }
}
