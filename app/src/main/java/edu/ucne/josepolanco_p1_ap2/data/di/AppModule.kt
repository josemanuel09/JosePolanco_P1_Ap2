package edu.ucne.josepolanco_p1_ap2.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.josepolanco_p1_ap2.data.local.database.ParcialRoom
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideParcial1DB(@ApplicationContext applicationContext: Context)=
        Room.databaseBuilder(
            applicationContext,
            ParcialRoom::class.java,
            "Parcial1.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideVentaDao(parcialRoom: ParcialRoom) = parcialRoom.VentaDao()

}