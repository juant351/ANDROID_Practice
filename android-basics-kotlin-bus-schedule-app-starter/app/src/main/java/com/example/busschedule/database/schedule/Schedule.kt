package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    /* This two columns are not nullable. You can specify that with @NonNullable,
     * but Kotlin do that by not using "?" at the end of the type.
     */
    @ColumnInfo(name = "stop_name") val stopName: String,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)
