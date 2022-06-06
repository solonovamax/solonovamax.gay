package gay.solonovamax.flexmark.twemoji.internal

import com.vladsch.flexmark.util.data.DataHolder
import gay.solonovamax.flexmark.twemoji.CustomEmojiExtension


class EmojiOptions(options: DataHolder) {
    val emojiImagePath = CustomEmojiExtension.EMOJI_IMAGE_PATH.get(options)
    val unknownEmojiImagePath = CustomEmojiExtension.EMOJI_IMAGE_PATH_UNKNOWN.get(options).takeUnless {
        it.isEmpty()
    } ?: CustomEmojiExtension.EMOJI_IMAGE_PATH.get(options)
    
    // val emojiHostPrecedence = TwemojiExtension.EMOJI_HOST_PRECEDENCE.get(options)
    // val emojiTypePrecedence = TwemojiExtension.EMOJI_TYPE_PREFERENCE.get(options)
    
    val emojiSizeAttribute = CustomEmojiExtension.EMOJI_SIZE_ATTR.get(options)
    val emojiAlignmentAttribute = CustomEmojiExtension.EMOJI_ALIGNMENT_ATTR.get(options)
    val emojiClassAttribute = CustomEmojiExtension.EMOJI_CLASS_ATTR.get(options)
    
    val renderUnknownEmoji = CustomEmojiExtension.EMOJI_RENDER_UNKNOWN.get(options)
}