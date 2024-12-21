package com.apsmt.notes.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note")
data class Notes(
    val title: String,
    val description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int
): Parcelable
