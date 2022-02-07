package com.star.programmingtechnologies.di

import android.content.ComponentCallbacks
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.star.programmingtechnologies.data.local.UserDao
import com.star.programmingtechnologies.data.local.UserDatabase
import com.star.programmingtechnologies.data.util.DATABASE_NAME
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
    fun provideDatabase(
        @ApplicationContext context: Context,
        callbacks: UserDatabase.Callback
    ) = Room.databaseBuilder(context,UserDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(callbacks)
        .build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase) : UserDao = db.userDao()

}