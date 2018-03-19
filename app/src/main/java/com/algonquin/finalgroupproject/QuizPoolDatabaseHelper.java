package com.algonquin.finalgroupproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Russell on 3/18/2018.
 */

public class QuizPoolDatabaseHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int QUIZ_DATABASE_VERSION = 1;

    // Database Name
    private static final String QUIZ_DATABASE_NAME = "Quiz.db";

    // Table name
    public static final String QUIZ_TABLE_NAME = "quiz_pool";

    // Table Columns names
    private static final String QUIZ_KEY_ID = "id";
    public static final String QUIZ_KEY_QUESTION = "quiz";
    public static String QUIZ_KEY_ANSWER = "answer";

    public QuizPoolDatabaseHelper(Context ctx) {
        super(ctx, QUIZ_DATABASE_NAME, null, QUIZ_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + QUIZ_TABLE_NAME +
                " (" + QUIZ_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUIZ_KEY_QUESTION + " TEXT);";
        try {
            Log.i("QuizPoolDatabaseHelper", query);
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("QuizPoolDatabaseHelper", e.getMessage());
        }
        Log.i("QuizPoolDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE_NAME + ";");
        onCreate(db);
        Log.i("QuizPoolDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion= " + newVer);
    }
}
