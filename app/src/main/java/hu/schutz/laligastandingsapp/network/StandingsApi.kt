package hu.schutz.laligastandingsapp.network

import hu.schutz.laligastandingsapp.data.StandingsResponse
import retrofit2.http.GET

interface StandingsApi {

    companion object {
        const val BASE_URL = "https://api-football-standings.azharimm.site"
    }

    @GET("/leagues/esp.1/standings?season=2021&sort=asc")
    suspend fun getStandings(): StandingsResponse
}