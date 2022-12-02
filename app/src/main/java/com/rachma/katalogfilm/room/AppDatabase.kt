package com.rachma.katalogfilm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rachma.katalogfilm.classes.Movie

// Mendeklaasikan database dengan entity yang berupa array dari class movie dan mempunyai versi berupa 1
@Database(entities = arrayOf(Movie::class), version = 1)
// Mendeklarasikan class yang berupa AppDatabase untuk RoomDatabase
abstract class AppDatabase : RoomDatabase(){
    // Mendeklaraskan fungsi movieDao
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        // Untuk mendeklarasikan variabel yang bernama Instance
        private  var INSTANCE: AppDatabase? = null
        // Mendeklarasikan fungsi untuk mendapatkan database dan isinya
        // Mendeklarasikan variabel yang berupa tempInstance
        // Dan jika nilai dari variabel tempInstance adalah null, maka akan mengembalikan tempInstance
        fun  getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return  tempInstance
            }

            // Untuk membuat database yang bernama user_database
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}