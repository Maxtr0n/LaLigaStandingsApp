package hu.schutz.laligastandingsapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.schutz.laligastandingsapp.persistence.StandingsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): StandingsDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            StandingsDatabase::class.java,
            "team_database.db"
        ).build()
    }
}