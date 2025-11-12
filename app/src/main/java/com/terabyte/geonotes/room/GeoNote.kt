package com.terabyte.geonotes.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.terabyte.geonotes.GEO_NOTE_DEFAULT_COLOR
import com.terabyte.geonotes.GEO_NOTE_DEFAULT_LATITUDE
import com.terabyte.geonotes.GEO_NOTE_DEFAULT_LONGITUDE
import java.util.Date
import java.util.UUID

@Entity(tableName = "geo_notes")
data class GeoNote(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double,
    @ColumnInfo(name = "note_text") var text: String,
    @ColumnInfo(name = "date") var date: Date = Date(),
    @ColumnInfo(name = "color") var color: Int
) : Parcelable {
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeString(id.toString())
            writeDouble(latitude)
            writeDouble(longitude)
            writeString(text)
            writeLong(date.time)
            writeInt(color)
        }
    }

    companion object CREATOR : Parcelable.Creator<GeoNote> {
        override fun createFromParcel(source: Parcel): GeoNote? {
            return GeoNote(
                id = UUID.fromString(source.readString()),
                latitude = source.readDouble(),
                longitude = source.readDouble(),
                text = source.readString() ?: "",
                date = Date(source.readLong()),
                color = source.readInt()
            )
        }

        override fun newArray(size: Int) = arrayOfNulls<GeoNote?>(size)

        fun getDefault(): GeoNote {
            return GeoNote(
                latitude = GEO_NOTE_DEFAULT_LATITUDE,
                longitude = GEO_NOTE_DEFAULT_LONGITUDE,
                text = "",
                color = GEO_NOTE_DEFAULT_COLOR
            )
        }
    }
}
