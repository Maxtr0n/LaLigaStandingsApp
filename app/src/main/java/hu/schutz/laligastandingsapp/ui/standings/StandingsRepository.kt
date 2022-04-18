package hu.schutz.laligastandingsapp.ui.standings

import hu.schutz.laligastandingsapp.data.Team
import hu.schutz.laligastandingsapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StandingsRepository {
    fun getTeams(): Flow<Resource<List<Team>>>
}