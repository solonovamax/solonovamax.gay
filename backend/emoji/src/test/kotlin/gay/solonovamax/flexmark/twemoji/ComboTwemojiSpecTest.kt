package gay.solonovamax.flexmark.twemoji

import com.vladsch.flexmark.core.test.util.RendererSpecTest
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.test.util.spec.ResourceLocation
import com.vladsch.flexmark.test.util.spec.SpecExample
import com.vladsch.flexmark.util.data.DataHolder
import com.vladsch.flexmark.util.data.MutableDataSet
import org.junit.runners.Parameterized


class ComboTwemojiSpecTest(example: SpecExample) : RendererSpecTest(example, optionsMap, OPTIONS) {
    companion object {
        private const val SPEC_RESOURCE = "/ext_emoji_ast_spec.md"
        
        private val RESOURCE_LOCATION = ResourceLocation.of(SPEC_RESOURCE)
        private val OPTIONS: DataHolder = MutableDataSet()
                .set(Parser.EXTENSIONS, listOf(CustomEmojiExtension.create()))
                .set(CustomEmojiExtension.EMOJI_IMAGE_PATH, "/img/")
                .toImmutable()
        private val optionsMap: MutableMap<String, DataHolder> = HashMap()
        
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): List<Array<Any>> {
            return getTestData(RESOURCE_LOCATION)
        }
        
        init {
            // optionsMap["use-github"] = MutableDataSet().set(TwemojiExtension.EMOJI_HOST_PRECEDENCE, EmojiHostPreference.GITHUB)
            // optionsMap["use-cheat"] = MutableDataSet().set(TwemojiExtension.EMOJI_HOST_PRECEDENCE, EmojiHostPreference.EMOJI_CHEAT_SHEET)
            // optionsMap["prefer-github"] = MutableDataSet().set(TwemojiExtension.EMOJI_HOST_PRECEDENCE, EmojiHostPreference.ANY_GITHUB_PREFERRED)
            // optionsMap["prefer-cheat"] = MutableDataSet().set(TwemojiExtension.EMOJI_HOST_PRECEDENCE, EmojiHostPreference.ANY_EMOJI_CHEAT_SHEET_PREFERRED)
            // optionsMap["unicode"] = MutableDataSet().set(TwemojiExtension.EMOJI_TYPE_PREFERENCE, EmojiTypePreference.UNICODE_FALLBACK_TO_IMAGE)
            // optionsMap["unicode-only"] = MutableDataSet().set(TwemojiExtension.EMOJI_TYPE_PREFERENCE, EmojiTypePreference.UNICODE_ONLY)
            optionsMap["size"] = MutableDataSet().set(CustomEmojiExtension.EMOJI_SIZE_ATTR, 40)
            optionsMap["no-size"] = MutableDataSet().set(CustomEmojiExtension.EMOJI_SIZE_ATTR, 0)
            optionsMap["no-align"] = MutableDataSet().set(CustomEmojiExtension.EMOJI_ALIGNMENT_ATTR, "")
        }
    }
}