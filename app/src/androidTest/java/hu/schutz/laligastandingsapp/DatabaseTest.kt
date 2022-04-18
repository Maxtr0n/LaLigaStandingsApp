package hu.schutz.laligastandingsapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.schutz.laligastandingsapp.data.Team
import hu.schutz.laligastandingsapp.network.StandingsApi
import hu.schutz.laligastandingsapp.persistence.StandingsDatabase
import hu.schutz.laligastandingsapp.persistence.TeamDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var db: StandingsDatabase
    private lateinit var teamDao: TeamDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, StandingsDatabase::class.java).build()
        teamDao = db.teamDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertTeamsAndRead() = runBlocking {
        val team = Team(
            "team1",
            "logo",
            10,
            5,
            3,
            1,
            1,
            1
        )
        teamDao.insertTeams(listOf(team))
        val teams = teamDao.getAllTeams().first()
        assertThat(teams[0], equalTo(team))
    }
}