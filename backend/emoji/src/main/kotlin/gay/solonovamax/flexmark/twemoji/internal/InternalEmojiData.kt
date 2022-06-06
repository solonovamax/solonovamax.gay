package gay.solonovamax.flexmark.twemoji.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class InternalEmojiData(
    @SerialName("name")
    override val name: String,
    @SerialName("unified")
    override val unified: String,
    @SerialName("short_names")
    override val shortNames: List<String> = listOf(),
    @SerialName("category")
    override val category: String,
    @SerialName("subcategory")
    override val subCategory: String,
                                     ) : EmojiData {
    
}