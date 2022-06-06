package gay.solonovamax.flexmark.twemoji.internal

import com.vladsch.flexmark.html.HtmlWriter
import com.vladsch.flexmark.html.renderer.NodeRenderer
import com.vladsch.flexmark.html.renderer.NodeRendererContext
import com.vladsch.flexmark.html.renderer.NodeRendererFactory
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler
import com.vladsch.flexmark.util.data.DataHolder
import gay.solonovamax.flexmark.twemoji.EmojiNode


class EmojiNodeRenderer private constructor(options: DataHolder) : NodeRenderer {
    private val options: EmojiOptions = EmojiOptions(options)
    
    override fun getNodeRenderingHandlers(): Set<NodeRenderingHandler<*>> {
        val set = HashSet<NodeRenderingHandler<*>>()
        set.add(NodeRenderingHandler(EmojiNode::class.java, ::render))
        return set
    }
    
    private fun render(emojiNode: EmojiNode, context: NodeRendererContext, html: HtmlWriter) {
        val renderer = EmojiRenderer.resolveRenderer(emojiNode)
        
        renderer.render(context, html, options)
    }
    
    companion object : NodeRendererFactory {
        override fun apply(options: DataHolder): NodeRenderer {
            return EmojiNodeRenderer(options)
        }
    }
}