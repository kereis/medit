package com.github.kereis.medit.adapters.explorer.room.time

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.github.kereis.medit.domain.mapping.DataMapper
import java.time.Clock
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@ProvidedTypeConverter
class DateTimeTypeConverter(
    private val formatter: DateTimeFormatter
): DataMapper<String, OffsetDateTime> {

    @TypeConverter // Android Room annotation for marking converters
    override fun toTargetType(source: String): OffsetDateTime =
        formatter.parse(source, OffsetDateTime::from)

    @TypeConverter
    override fun toSourceType(target: OffsetDateTime): String =
        target.format(formatter)
}