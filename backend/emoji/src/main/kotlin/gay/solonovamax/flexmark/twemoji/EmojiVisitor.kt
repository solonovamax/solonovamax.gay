package gay.solonovamax.flexmark.twemoji

interface EmojiVisitor {
    fun visit(node: EmojiNode)
}