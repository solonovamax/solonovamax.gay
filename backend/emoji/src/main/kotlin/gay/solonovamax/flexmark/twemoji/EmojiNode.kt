package gay.solonovamax.flexmark.twemoji

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.util.ast.DelimitedNode
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.TextContainer
import com.vladsch.flexmark.util.ast.TextContainer.F_FOR_HEADING_ID
import com.vladsch.flexmark.util.misc.BitFieldSet
import com.vladsch.flexmark.util.sequence.BasedSequence
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder


/**
 * An emoji node containing emoji shortcut text
 */
data class EmojiNode(
    private var openingMarker: BasedSequence,
    private var text: BasedSequence,
    private var closingMarker: BasedSequence
                    ) :
        Node(openingMarker.baseSubSequence(openingMarker.startOffset, closingMarker.endOffset)), DelimitedNode, TextContainer {
    
    override fun getAstExtra(out: StringBuilder) {
        delimitedSegmentSpanChars(out, openingMarker, text, closingMarker, "text")
    }
    
    override fun getSegments(): Array<BasedSequence> = arrayOf(openingMarker, text, closingMarker)
    
    override fun getOpeningMarker(): BasedSequence {
        return openingMarker
    }
    
    override fun setOpeningMarker(openingMarker: BasedSequence) {
        this.openingMarker = openingMarker
    }
    
    override fun getText(): BasedSequence {
        return text
    }
    
    override fun setText(text: BasedSequence) {
        this.text = text
    }
    
    override fun getClosingMarker(): BasedSequence {
        return closingMarker
    }
    
    override fun setClosingMarker(closingMarker: BasedSequence) {
        this.closingMarker = closingMarker
    }
    
    override fun collectText(
        out: ISequenceBuilder<out ISequenceBuilder<*, BasedSequence>, BasedSequence>,
        flags: Int,
        nodeVisitor: NodeVisitor
                            ): Boolean {
        if (BitFieldSet.any(flags.toLong(), F_FOR_HEADING_ID.toLong())) {
            if (HtmlRenderer.HEADER_ID_ADD_EMOJI_SHORTCUT.get(document)) {
                out.append(text)
            }
        }
        return false
    }
    
    
}