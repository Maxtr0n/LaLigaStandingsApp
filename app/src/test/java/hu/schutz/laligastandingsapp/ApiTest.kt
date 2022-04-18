package hu.schutz.laligastandingsapp

import com.google.common.truth.Truth.assertThat
import hu.schutz.laligastandingsapp.network.StandingsApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    private val mockWebServer = MockWebServer()
    private lateinit var api: StandingsApi

    @Before
    fun setUp() {
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StandingsApi::class.java)

    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun getTeams_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("success_response.json")
            val responseBody = api.getStandings()
            val request = mockWebServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/leagues/esp.1/standings?season=2021&sort=asc")
            assertThat(responseBody.status).isEqualTo("true")
            assertThat(responseBody.data.name).isEqualTo("Spanish LaLiga")
            assertThat(responseBody.data.standings[0].team.name).isEqualTo("Real Madrid")
        }
    }
}