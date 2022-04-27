package hu.schutz.laligastandingsapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.schutz.laligastandingsapp.data.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface  TeamDao {

    @Query("SELECT * FROM teams ORDER BY rank")
    fun getAllTeams(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<Team>)

    @Query("DELETE FROM teams")
    suspend fun deleteAllTeams()
}