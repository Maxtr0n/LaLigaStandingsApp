package hu.schutz.laligastandingsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey val name: String,
    val logo: String,
    val points: Int,
    val gamesPlayed: Int,
    val wins: Int,
    val ties: Int,
    val losses: Int,
    val rank: Int
)