package com.github.kereis.medit.adapters.explorer.room.files

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.github.kereis.medit.domain.mapping.DataMapper
import java.net.URI
import java.net.URISyntaxException

@ProvidedTypeConverter
class URITypeConverter : DataMapper<String, URI> {

    @TypeConverter // Android Room annotation for marking converters
    @Throws(URISyntaxException::class)
    override fun toTargetType(source: String) = URI(source)

    @TypeConverter // Android Room annotation for marking converters
    override fun toSourceType(target: URI): String = target.toString()
}
