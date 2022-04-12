package hu.schutz.laligastandingsapp.model

data class League (
    val name: String,
    val abbreviation: String,
    val seasonDisplay: String,
    val season: Int,
    val standings: ArrayList<StandingsObject>
        )