package hu.schutz.laligastandingsapp.ui.standings

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    repository: StandingsRepository
) : ViewModel() {

    val teams = repository.getTeams().asLiveData()
}