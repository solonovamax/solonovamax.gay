package gay.solonovamax.website.util

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import java.io.InputStream

@OptIn(InternalSerializationApi::class)
inline fun <reified T : Any> readYaml(stream: InputStream): T {
    return Yaml.default.decodeFromStream(T::class.serializer(), stream)
}
