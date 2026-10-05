package com.example.noteapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 3)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        val migration = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE notes ADD COLUMN color INTEGER NOT NULL DEFAULT 1757885279030")
                db.execSQL("ALTER TABLE notes ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0")
                db.execSQL("UPDATE  notes SET timestamp = strftime('%s','now') WHERE timestamp = 0")
            }
        }

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db"
                ).addMigrations(migration)
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}
