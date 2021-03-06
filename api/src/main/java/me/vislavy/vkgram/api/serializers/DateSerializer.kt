package me.vislavy.vkgram.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

internal object DateSerializer : KSerializer<Date> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Date) {
        val date = (value.time / 1000)
        encoder.encodeLong(date)
    }

    override fun deserialize(decoder: Decoder): Date {
        return Date(decoder.decodeLong() * 1000)
    }
}