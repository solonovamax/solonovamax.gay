package gay.solonovamax.flexmark.twemoji.internal

import com.vladsch.flexmark.parser.InlineParser
import com.vladsch.flexmark.parser.core.delimiter.Delimiter
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor
import com.vladsch.flexmark.parser.delimiter.DelimiterRun
import com.vladsch.flexmark.util.misc.CharPredicate
import com.vladsch.flexmark.util.sequence.BasedSequence
import gay.solonovamax.flexmark.twemoji.EmojiNode


object EmojiDelimiterProcessor : DelimiterProcessor {
    override fun getOpeningCharacter() = ':'
    
    override fun getClosingCharacter() = ':'
    
    override fun getMinLength() = 1
    
    override fun canBeOpener(
        before: String,
        after: String,
        leftFlanking: Boolean,
        rightFlanking: Boolean,
        beforeIsPunctuation: Boolean,
        afterIsPunctuation: Boolean,
        beforeIsWhitespace: Boolean,
        afterIsWhiteSpace: Boolean
                            ) = leftFlanking && !"0123456789".contains(before)
    
    override fun canBeCloser(
        before: String,
        after: String,
        leftFlanking: Boolean,
        rightFlanking: Boolean,
        beforeIsPunctuation: Boolean,
        afterIsPunctuation: Boolean,
        beforeIsWhitespace: Boolean,
        afterIsWhiteSpace: Boolean
                            ) = rightFlanking && !"0123456789".contains(after)
    
    override fun skipNonOpenerCloser() = true
    
    override fun getDelimiterUse(opener: DelimiterRun, closer: DelimiterRun) = 1
    
    override fun unmatchedDelimiterNode(inlineParser: InlineParser, delimiter: DelimiterRun) = null
    
    override fun process(opener: Delimiter, closer: Delimiter, delimitersUsed: Int) {
        // Normal case, wrap nodes between delimiters in emoji node.
        // don't allow any spaces between delimiters
        if (opener.input.subSequence(opener.endIndex, closer.startIndex).indexOfAny(CharPredicate.WHITESPACE) == -1) {
            val emojiNode = EmojiNode(opener.getTailChars(delimitersUsed), BasedSequence.NULL, closer.getLeadChars(delimitersUsed))
            
            opener.moveNodesBetweenDelimitersTo(emojiNode, closer)
        } else {
            opener.convertDelimitersToText(delimitersUsed, closer)
        }
    }
}