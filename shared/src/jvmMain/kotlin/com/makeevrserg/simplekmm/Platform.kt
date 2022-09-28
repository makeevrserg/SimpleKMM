package com.makeevrserg.simplekmm

import com.mongodb.BasicDBObject
import okio.ByteString
import okio.ByteString.Companion.toByteString
import org.bson.BasicBSONEncoder
import org.bson.RawBsonDocument
import org.bson.json.JsonWriterSettings

class DesktopPlatform : Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()
actual fun bsonToString(byteArray: ByteArray): String {
    val settings = JsonWriterSettings.builder()
        .build()
    return RawBsonDocument(byteArray).toJson(settings)
}

actual fun stringToBson(string: String): ByteString {
    return BasicBSONEncoder().encode(BasicDBObject.parse(string)).toByteString()
}