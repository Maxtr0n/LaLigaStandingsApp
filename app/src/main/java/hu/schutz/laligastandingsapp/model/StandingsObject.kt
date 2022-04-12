package hu.schutz.laligastandingsapp.model

data class StandingsObject (
    val team: TeamDetails,
    val stats: ArrayList<TeamStats>
        )