package com.example.daytworoomresearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.daytworoomresearch.feature.shared.model.Person
import com.example.daytworoomresearch.utils.constants.DatabaseConstants

@Database(
    entities =[Person::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPersonInfo () : PersonDao
    companion object {
        private var appDatabase : AppDatabase? =null

        fun getAppDatabase(context : Context?) =
            if (appDatabase !=null){
                appDatabase
            }else{
                if (context != null){
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DatabaseConstants.databaseName
                    )
                        .allowMainThreadQueries()
                        .build()
                }else{
                    null
                }
            }
    }
}