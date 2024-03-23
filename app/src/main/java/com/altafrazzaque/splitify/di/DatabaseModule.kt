package com.altafrazzaque.splitify.di

import android.content.Context
import androidx.room.Room
import com.altafrazzaque.splitify.database.AppDatabase
import com.altafrazzaque.splitify.database.ExpenseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideHistoryDao(database: AppDatabase): ExpenseDao {
        return database.expenseDao
    }

}