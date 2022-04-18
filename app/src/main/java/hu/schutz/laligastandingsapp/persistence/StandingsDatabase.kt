package hu.schutz.laligastandingsapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.schutz.laligastandingsapp.data.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class StandingsDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}