package eu.plantpal.app.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import java.lang.reflect.Type

class OneOrManyStringAdapter : JsonAdapter<List<String>>() {

    @FromJson
    override fun fromJson(reader: JsonReader): List<String> {
        return when (reader.peek()) {
            JsonReader.Token.BEGIN_ARRAY -> {
                val list = mutableListOf<String>()
                reader.beginArray()
                while (reader.hasNext()) {
                    list.add(reader.nextString())
                }
                reader.endArray()
                list
            }

            JsonReader.Token.BEGIN_OBJECT -> {
                reader.skipValue()
                emptyList()
            }

            JsonReader.Token.STRING -> listOf(reader.nextString())
            JsonReader.Token.NULL -> {
                reader.nextNull<Unit>()
                emptyList()
            }

            else -> {
                reader.skipValue()
                emptyList()
            }
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: List<String>?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.beginArray()
        for (item in value) {
            writer.value(item)
        }
        writer.endArray()
    }
}

class OneOrManyAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        val elementType = Types.collectionElementType(type, List::class.java)

        return if (rawType == List::class.java && elementType == String::class.java) {
            OneOrManyStringAdapter()
        } else {
            null
        }
    }
}
