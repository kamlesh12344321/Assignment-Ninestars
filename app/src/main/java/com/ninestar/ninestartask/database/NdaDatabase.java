package com.ninestar.ninestartask.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ninestar.ninestartask.app.TaskApplication;
import com.ninestar.ninestartask.model.DocsItem;
import com.ninestar.ninestartask.model.ModelConverterForTable;

import org.jetbrains.annotations.NotNull;

@Database(entities = {DocsItem.class}, version = 1, exportSchema = false)
@TypeConverters({ModelConverterForTable.class})
public abstract class NdaDatabase extends RoomDatabase {

    private static volatile NdaDatabase s_INSTANCE;

    public abstract DAO_DocsItem mDao_docsItem();

    /* Creating instance of ndaDatabase object and building database */
    public static NdaDatabase getInstance() {
        if (s_INSTANCE == null) {
            synchronized (NdaDatabase.class) {
                if (s_INSTANCE == null) {
                    s_INSTANCE = Room.databaseBuilder(TaskApplication.getContext(), NdaDatabase.class, "nda_database.db")
                            .build();
                }
            }
        }
        return s_INSTANCE;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
