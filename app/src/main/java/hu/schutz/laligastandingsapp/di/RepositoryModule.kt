package hu.schutz.laligastandingsapp.di

import android.support.v4.os.IResultReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.schutz.laligastandingsapp.network.StandingsApi
import hu.schutz.laligastandingsapp.persistence.StandingsDatabase
import hu.schutz.laligastandingsapp.ui.standings.DefaultStandingsRepository
import hu.schutz.laligastandingsapp.ui.standings.StandingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideStandingsRepository(
        standingsApi: StandingsApi,
        standingsDatabase: StandingsDatabase
    ): StandingsRepository {
        return DefaultStandingsRepository(standingsApi, standingsDatabase)
    }
}