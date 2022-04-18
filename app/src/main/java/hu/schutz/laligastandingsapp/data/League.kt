package hu.schutz.laligastandingsapp.data

data class League (
    val name: String,
    val abbreviation: String,
    val seasonDisplay: String,
    val season: Int,
    val standings: List<StandingsObject>
        )