package gay.solonovamax.flexmark.twemoji

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataSet
import com.vladsch.flexmark.util.data.MutableDataSet
import org.junit.Test

class CustomEmojiTest {
    lateinit var parser: Parser
    lateinit var renderer: HtmlRenderer
    
    @Test
    fun emoji() {
        withOptions(OPTIONS)
        val document = parser.parse("Hello! :+1: :tada: :testingabcd:")
        
        val output = renderer.render(document)
        
        println(output)
    }
    
    @Test
    fun customEmoji() {
        withOptions(OPTIONS.set(CustomEmojiExtension.EMOJI_RENDER_UNKNOWN, true))
        println("ya")
        val document = parser.parse("Hello! :+1: :tada: :testing_abcd: :one_two_three:")
        
        val output = renderer.render(document)
        
        println(output)
    }
    
    private fun withOptions(options: DataSet) {
        parser = Parser.builder(options)
                .build()
        renderer = HtmlRenderer.builder(options)
                .escapeHtml(false)
                .indentSize(0)
                .percentEncodeUrls(true)
                .build()
    }
    
    companion object {
        private val OPTIONS
            get() = MutableDataSet()
                    .set(Parser.EXTENSIONS, listOf(CustomEmojiExtension.create()))
        
    }
}