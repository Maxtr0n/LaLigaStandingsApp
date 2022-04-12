package hu.schutz.laligastandingsapp.network

import hu.schutz.laligastandingsapp.model.StandingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface StandingsApi {

    @GET("/leagues/esp.1/standings?season=2021&sort=asc")
    suspend fun getStandings(): Response<StandingsResponse>
}