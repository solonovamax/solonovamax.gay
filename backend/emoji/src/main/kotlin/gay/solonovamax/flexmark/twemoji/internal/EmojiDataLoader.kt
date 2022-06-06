package gay.solonovamax.flexmark.twemoji.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@OptIn(ExperimentalSerializationApi::class)
object EmojiDataLoader {
    private val format = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
    
    private const val emojiDataPath = "/emoji.json"
    private val internalEmojiData: MutableList<EmojiData>
    private val internalEmojiShortcodes: MutableMap<String, EmojiData>
    
    val emojiShortcodes: Map<String, EmojiData>
        get() = internalEmojiShortcodes
    
    val emojiData: List<EmojiData>
        get() = internalEmojiData
    
    init {
        val stream = javaClass.getResourceAsStream(emojiDataPath)!!
        internalEmojiData = format.decodeFromStream<List<InternalEmojiData>>(stream).toMutableList()
        internalEmojiShortcodes = internalEmojiData.flatMap { data ->
            data.shortNames.map { it to data }
        }.toMap().toMutableMap()
    }
}
