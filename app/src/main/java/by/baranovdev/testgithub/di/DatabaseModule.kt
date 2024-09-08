package by.baranovdev.testgithub.di

import android.content.Context
import androidx.room.Room
import by.baranovdev.testgithub.data.database.AppDatabase
import by.baranovdev.testgithub.data.database.dao.PathDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "test_github.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGithubRepositoryDao(db: AppDatabase): PathDao {
        return db.pathDao()
    }

}