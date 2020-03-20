package com.example.thoughts.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.example.thoughts.Thought;

import java.util.UUID;


public class ThoughtCursorWrapper extends CursorWrapper {

    public ThoughtCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Thought getThought() {
        String uuidString = getString(getColumnIndex(ThoughtDbSchema.ThoughtTable.Cols.UUID));
        String title = getString(getColumnIndex(ThoughtDbSchema.ThoughtTable.Cols.TITLE));
        String content = getString(getColumnIndex(ThoughtDbSchema.ThoughtTable.Cols.CONTENT));

        Thought thought = new Thought(UUID.fromString(uuidString));
        thought.setTitle(title);
        thought.setContent(content);

        return thought;
    }
}
