package com.example.thoughts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thoughts.database.ThoughtBaseHelper;
import com.example.thoughts.database.ThoughtCursorWrapper;
import com.example.thoughts.database.ThoughtDbSchema.ThoughtTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ThoughtLab {
    private static ThoughtLab sThoughtLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ThoughtLab get(Context context) {
        if  (sThoughtLab == null) {
            sThoughtLab = new ThoughtLab(context);
        }
        return  sThoughtLab;
    }

    private ThoughtLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ThoughtBaseHelper(mContext).getWritableDatabase();

    }

    public List<Thought> getThoughts() {
       List<Thought> thoughts = new ArrayList<>();

       ThoughtCursorWrapper cursor = queryThoughts(null, null);

       try {
           cursor.moveToFirst();
           while (!cursor.isAfterLast()) {
               thoughts.add(cursor.getThought());
               cursor.moveToNext();
           }
       } finally {
           cursor.close();
       }

       return thoughts;
    }

    public void addThought(Thought thought) {
        ContentValues values = getContentValues(thought);
        mDatabase.insert(ThoughtTable.NAME, null, values);
    }

    public Thought getThought(UUID id) {

        ThoughtCursorWrapper cursor = queryThoughts(
            ThoughtTable.Cols.UUID + " = ?",
            new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getThought();
        } finally {
            cursor.close();
        }
    }

    public void deleteThought(Thought thought) {
        String uuidString = thought.getId().toString();
        mDatabase.delete(ThoughtTable.NAME,
                ThoughtTable.Cols.UUID + " = ?", new String[] {uuidString} );
    }

    public void updateThought(Thought thought) {
        String uuidString = thought.getId().toString();
        ContentValues values = getContentValues(thought);

        mDatabase.update(ThoughtTable.NAME, values, ThoughtTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private ThoughtCursorWrapper queryThoughts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ThoughtTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ThoughtCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Thought thought) {
        ContentValues values = new ContentValues();
        values.put(ThoughtTable.Cols.UUID, thought.getId().toString());
        values.put(ThoughtTable.Cols.TITLE, thought.getTitle());
        values.put(ThoughtTable.Cols.CONTENT, thought.getContent());

        return values;
    }

}
