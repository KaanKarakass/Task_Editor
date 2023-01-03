package com.kaankarakas.bookdatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        //if database doesnt already exist, getInstace method goes and builds it
        fun getInstance(context:Context): TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}