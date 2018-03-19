package com.algonquin.finalgroupproject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewQuizPool extends Activity {

    private ListView listview;
    private ArrayList<String> quizPool = new ArrayList<>();

    //Get a writable database.
    private QuizPoolDatabaseHelper dbHelper = null;
    private SQLiteDatabase db = null;

    //Get table name and column name.
    String tableName = dbHelper.QUIZ_TABLE_NAME;
    String keyQuiz = dbHelper.QUIZ_KEY_QUESTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz_pool);

        dbHelper = new QuizPoolDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        listview = findViewById(R.id.listview_quizpool);

        //in this case, “this” is the ChatWindow, which is-A Context object
        final QuizAdapter messageAdapter = new QuizAdapter(this);
        listview.setAdapter(messageAdapter);

        String query = "SELECT * FROM " + tableName +";";
        Cursor c = db.rawQuery(query, null);

        //Read existed messages from database.
        c.moveToFirst();
        while(!c.isAfterLast()){

            String str = c.getString( c.getColumnIndex( dbHelper.QUIZ_KEY_QUESTION) );
            quizPool.add(str);

            //this restarts the process of getCount() & getView() to retrieve chat history
            messageAdapter.notifyDataSetChanged();

            Log.i("ViewQuizPool", "SQL MESSAGE:" + str );
            c.moveToNext();
        }

        //Print colomn name.
        Log.i("ViewQuizPool", "Cursor’s  column count = " + c.getColumnCount());
        for(int i = 0; c.getColumnCount() > i; i++){
            Log.i("ViewQuizPool", "Coloumn " + i + " : " + c.getColumnName(i));
        }
    }

    private class QuizAdapter extends ArrayAdapter<String> {
        public QuizAdapter(Context ctx) {
            super(ctx, 0);
        }

        LayoutInflater inflater = ViewQuizPool.this.getLayoutInflater();
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View result;
            TextView question;
            TextView answerA;
            TextView answerB;
            TextView answerC;
            TextView answerD;

            result = inflater.inflate(R.layout.quiz_pool_layout, null);
            question = result.findViewById(R.id.quizList);
            answerA = result.findViewById(R.id.quiz_Answer_A);
            answerB = result.findViewById(R.id.quiz_Answer_B);
            answerC = result.findViewById(R.id.quiz_Answer_C);
            answerD = result.findViewById(R.id.quiz_Answer_D);

            question.setText(getItem(position)); // get the string at position
            answerA.setText(getItem(position));
            answerB.setText(getItem(position));
            answerC.setText(getItem(position));
            answerD.setText(getItem(position));

            return result;
        }

        @Override
        public int getCount(){
            return quizPool.size();
        }

        @Override
        public String getItem(int position){
            return quizPool.get(position);
        }

        public long getId(int position){
            return position;
        }

    }
}
