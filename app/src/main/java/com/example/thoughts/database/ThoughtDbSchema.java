package com.example.thoughts.database;

public class ThoughtDbSchema {
    public static final class ThoughtTable {
        public static final String NAME = "thoughts";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
        }
    }
}
