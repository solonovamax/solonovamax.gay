package gay.solonovamax.website.util

import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension
import com.vladsch.flexmark.ext.autolink.AutolinkExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.typographic.TypographicExtension
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataHolder
import com.vladsch.flexmark.util.data.MutableDataSet
import gay.solonovamax.flexmark.twemoji.CustomEmojiExtension

fun flexmarkOptions(emojiPath: String): DataHolder {
    val options = MutableDataSet()
    
    options.set(HtmlRenderer.GENERATE_HEADER_ID, true)
    options.set(
            Parser.EXTENSIONS,
            listOf(
                    AnchorLinkExtension.create(),
                    AutolinkExtension.create(),
                    CustomEmojiExtension.create(),
                    StrikethroughSubscriptExtension.create(),
                    TaskListExtension.create(),
                    MediaTagsExtension.create(),
                    SuperscriptExtension.create(),
                    TablesExtension.create(),
                    TypographicExtension.create(),
                    YouTubeLinkExtension.create(),
                  ),
               )
    options.set(CustomEmojiExtension.EMOJI_IMAGE_PATH, emojiPath)
    
    
    return options
}