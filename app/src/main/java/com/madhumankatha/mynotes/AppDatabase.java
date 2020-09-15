package com.madhumankatha.mynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "NotesDB")
                    .allowMainThreadQueries()
                    .build();

            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
