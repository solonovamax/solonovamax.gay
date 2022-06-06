package gay.solonovamax.flexmark.twemoji.internal

interface EmojiData {
    val name: String
    val unified: String
    val shortNames: List<String>
    val category: String
    val subCategory: String
}
