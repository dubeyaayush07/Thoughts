package com.example.thoughts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThoughtBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "thoughtBase.db";

    public ThoughtBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + ThoughtDbSchema.ThoughtTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                ThoughtDbSchema.ThoughtTable.Cols.UUID + ", " +
                ThoughtDbSchema.ThoughtTable.Cols.TITLE + ", " +
                ThoughtDbSchema.ThoughtTable.Cols.CONTENT +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
