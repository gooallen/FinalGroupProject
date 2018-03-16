package com.algonquin.finalgroupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_quizCreater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_quizCreater = findViewById(R.id.button_quizCreater);
        btn_quizCreater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Jump to QuizCreaterActivity.
                Intent intent = new Intent(MainActivity.this, QuizCreatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
