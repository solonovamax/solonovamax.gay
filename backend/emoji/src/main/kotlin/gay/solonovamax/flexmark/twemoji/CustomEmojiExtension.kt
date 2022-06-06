package gay.solonovamax.flexmark.twemoji

import com.vladsch.flexmark.formatter.Formatter
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataKey
import com.vladsch.flexmark.util.data.MutableDataHolder
import gay.solonovamax.flexmark.twemoji.internal.EmojiDelimiterProcessor
import gay.solonovamax.flexmark.twemoji.internal.EmojiNodeFormatter
import gay.solonovamax.flexmark.twemoji.internal.EmojiNodeRenderer
import org.jetbrains.annotations.NotNull


/**
 * Extension for emoji shortcuts using Emoji-Cheat-Sheet.com.
 *
 *
 * Create it with [.create] and then configure it on the builders
 *
 *
 * The parsed emoji shortcuts text regions are turned into [EmojiNode] nodes.
 */
class CustomEmojiExtension : Parser.ParserExtension, HtmlRendererExtension, Formatter.FormatterExtension {
    override fun rendererOptions(options: MutableDataHolder) {
        
    }
    
    override fun parserOptions(options: MutableDataHolder) {
        
    }
    
    override fun extend(builder: Formatter.Builder) {
        builder.nodeFormatterFactory(EmojiNodeFormatter)
    }
    
    override fun extend(builder: Parser.Builder) {
        builder.customDelimiterProcessor(EmojiDelimiterProcessor)
    }
    
    override fun extend(@NotNull htmlRendererBuilder: HtmlRenderer.Builder, @NotNull rendererType: String) {
        htmlRendererBuilder.nodeRendererFactory(EmojiNodeRenderer)
    }
    
    companion object {
        val EMOJI_ALIGNMENT_ATTR = DataKey("EMOJI_ALIGNMENT_ATTR", "absmiddle")
        val EMOJI_SIZE_ATTR = DataKey("EMOJI_SIZE_ATTR", 24)
        val EMOJI_CLASS_ATTR = DataKey("ATTR_IMAGE_CLASS", "")
        
        val EMOJI_IMAGE_PATH = DataKey("EMOJI_IMAGE_PATH", "/img/")
        val EMOJI_IMAGE_PATH_UNKNOWN = DataKey("EMOJI_IMAGE_PATH_UNKNOWN", "")
        
        val EMOJI_RENDER_UNKNOWN = DataKey("EMOJI_RENDER_UNKNOWN", false)
        
        // val EMOJI_HOST_PRECEDENCE = DataKey(
        //         "EMOJI_HOST_PRECEDENCE",
        //         listOf(
        //                 EmojiHostPreference.LOCAL,
        //                 EmojiHostPreference.MAXCDN,
        //                 EmojiHostPreference.GITHUB,
        //               ),
        //                                    )
        // val EMOJI_TYPE_PREFERENCE = DataKey("USE_IMAGE_TYPE", EmojiTypePreference.SVG)
        
        fun create() = CustomEmojiExtension()
    }
}