package gay.solonovamax.flexmark.twemoji

import com.vladsch.flexmark.util.ast.VisitHandler


object EmojiVisitorExt {
    @JvmStatic
    fun <V : EmojiVisitor> visitHandlers(visitor: V): Array<VisitHandler<*>> {
        return arrayOf(VisitHandler(EmojiNode::class.java, visitor::visit))
    }
}