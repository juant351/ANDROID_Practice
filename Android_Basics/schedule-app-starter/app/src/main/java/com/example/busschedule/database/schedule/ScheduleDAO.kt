package com.example.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDAO {
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll() : Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopname ORDER BY arrival_time ASC")
    fun getByStopName(stopname : String) : Flow<List<Schedule>>
}