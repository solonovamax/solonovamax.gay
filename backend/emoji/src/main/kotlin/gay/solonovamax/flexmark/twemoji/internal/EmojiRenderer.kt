package gay.solonovamax.flexmark.twemoji.internal

import com.vladsch.flexmark.html.HtmlWriter
import com.vladsch.flexmark.html.renderer.LinkType
import com.vladsch.flexmark.html.renderer.NodeRendererContext
import gay.solonovamax.flexmark.twemoji.EmojiNode
import java.net.URI


sealed interface EmojiRenderer {
    val node: EmojiNode
    
    fun render(context: NodeRendererContext, html: HtmlWriter, options: EmojiOptions)
    
    class ImageEmojiRenderer(
        override val node: EmojiNode,
        val data: EmojiData,
                            ) : EmojiRenderer {
        override fun render(context: NodeRendererContext, html: HtmlWriter, options: EmojiOptions) {
            val normalizedLink = URI("${options.emojiImagePath}/${data.unified.lowercase()}.svg").normalize()
            val resolvedLink = context.resolveLink(LinkType.IMAGE, normalizedLink.toString(), null)
            
            html.attr("src", resolvedLink.url)
            html.attr("alt", data.name.split(' ').joinToString(separator = " ") {
                it.lowercase().replaceFirstChar { char -> char.uppercaseChar() }
            })
            
            if (options.emojiSizeAttribute != 0)
                html.attr("height", options.emojiSizeAttribute.toString())
                        .attr("width", options.emojiSizeAttribute.toString())
            
            if (options.emojiAlignmentAttribute.isNotEmpty())
                html.attr("align", options.emojiAlignmentAttribute)
            
            if (options.emojiClassAttribute.isNotEmpty())
                html.attr("class", options.emojiClassAttribute)
            
            html.withAttr(resolvedLink)
            html.tagVoid("img")
        }
    }
    
    class UnknownEmojiRenderer(
        override val node: EmojiNode,
        val name: String,
                              ) : EmojiRenderer {
        override fun render(context: NodeRendererContext, html: HtmlWriter, options: EmojiOptions) {
            println(options.renderUnknownEmoji)
            if (options.renderUnknownEmoji) {
                val normalizedLink = URI("${options.unknownEmojiImagePath}/$name.svg").normalize()
                val resolvedLink = context.resolveLink(LinkType.IMAGE, normalizedLink.toString(), null)
                
                html.attr("src", resolvedLink.url)
                html.attr("alt", name.replace('-', '_').split('_').joinToString(separator = " ") {
                    it.lowercase().replaceFirstChar { char -> char.uppercaseChar() }
                })
                
                if (options.emojiSizeAttribute != 0)
                    html.attr("height", options.emojiSizeAttribute.toString())
                            .attr("width", options.emojiSizeAttribute.toString())
                
                if (options.emojiAlignmentAttribute.isNotEmpty())
                    html.attr("align", options.emojiAlignmentAttribute)
                
                if (options.emojiClassAttribute.isNotEmpty())
                    html.attr("class", options.emojiClassAttribute)
                
                html.withAttr(resolvedLink)
                html.tagVoid("img")
            } else {
                html.text(":")
                context.renderChildren(node)
                html.text(":")
            }
        }
    }
    
    companion object {
        fun resolveRenderer(node: EmojiNode): EmojiRenderer {
            val emojiId = node.text.toString()
            
            val data = EmojiDataLoader.emojiShortcodes[emojiId]
            
            return if (data != null) {
                ImageEmojiRenderer(node, data)
            } else {
                UnknownEmojiRenderer(node, emojiId)
            }
        }
    }
}