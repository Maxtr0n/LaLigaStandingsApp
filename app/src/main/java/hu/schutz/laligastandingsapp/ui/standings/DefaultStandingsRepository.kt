package hu.schutz.laligastandingsapp.ui.standings

import androidx.room.withTransaction
import hu.schutz.laligastandingsapp.data.StandingsResponse
import hu.schutz.laligastandingsapp.data.Team
import hu.schutz.laligastandingsapp.network.StandingsApi
import hu.schutz.laligastandingsapp.persistence.StandingsDatabase
import hu.schutz.laligastandingsapp.util.networkBoundResource
import javax.inject.Inject

class DefaultStandingsRepository (
    private val api: StandingsApi,
    private val db: StandingsDatabase
) : StandingsRepository {
    private val teamDao = db.teamDao()

    override fun getTeams() = networkBoundResource(
        query = {
            teamDao.getAllTeams()
        },
        fetch = {
            api.getStandings()
        },
        saveFetchResult = { standingsResponse ->
            val teams = convertResponse(standingsResponse)
            db.withTransaction {
                teamDao.deleteAllTeams()
                teamDao.insertTeams(teams)
            }
        }
    )

    private fun convertResponse(standingsResponse: StandingsResponse): List<Team> {
        val teams = mutableListOf<Team>()
        for (standingsObject in standingsResponse.data.standings) {
            val name: String = standingsObject.team.name
            val logo: String = standingsObject.team.logos[0].href
            var points = 0
            var gamesPlayed = 0
            var wins = 0
            var ties = 0
            var losses = 0
            var rank = 0
            for (stat in standingsObject.stats) {
               when (stat.name) {
                   "wins" -> wins = stat.value
                   "losses" -> losses = stat.value
                   "ties" -> ties = stat.value
                   "gamesPlayed" -> gamesPlayed = stat.value
                   "points" -> points = stat.value
                   "rank" -> rank = stat.value
               }
            }

            val team = Team(name, logo, points, gamesPlayed, wins, ties, losses, rank)
            teams.add(team)
        }
        return teams.toList()
    }
}