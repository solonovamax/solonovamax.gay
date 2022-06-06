package gay.solonovamax.flexmark.twemoji.internal

import com.vladsch.flexmark.formatter.MarkdownWriter
import com.vladsch.flexmark.formatter.NodeFormatter
import com.vladsch.flexmark.formatter.NodeFormatterContext
import com.vladsch.flexmark.formatter.NodeFormatterFactory
import com.vladsch.flexmark.formatter.NodeFormattingHandler
import com.vladsch.flexmark.formatter.NodeFormattingHandler.CustomNodeFormatter
import com.vladsch.flexmark.util.data.DataHolder
import gay.solonovamax.flexmark.twemoji.EmojiNode


class EmojiNodeFormatter private constructor() : NodeFormatter {
    override fun getNodeClasses(): Set<Class<*>>? {
        return null
    }
    
    // only registered if assignTextAttributes is enabled
    override fun getNodeFormattingHandlers(): Set<NodeFormattingHandler<*>> {
        val set = HashSet<NodeFormattingHandler<*>>()
        set.add(NodeFormattingHandler(EmojiNode::class.java, CustomTwemojiNodeFormatter))
        return set
    }
    
    object CustomTwemojiNodeFormatter : CustomNodeFormatter<EmojiNode> {
        override fun render(node: EmojiNode, context: NodeFormatterContext, markdown: MarkdownWriter) {
            markdown.append(node.openingMarker)
            markdown.appendNonTranslating(node.text)
            markdown.append(node.closingMarker)
        }
        
    }
    
    companion object : NodeFormatterFactory {
        override fun create(options: DataHolder): NodeFormatter {
            return EmojiNodeFormatter()
        }
    }
}