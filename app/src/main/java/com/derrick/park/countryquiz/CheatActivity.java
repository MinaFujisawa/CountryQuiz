package com.derrick.park.countryquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
    private Button mShowButton;
    private TextView mAnswerTextView;
    private boolean mAnswer;
    private boolean mIsCheated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswer = getIntent().getBooleanExtra("answer", false);

        mAnswerTextView = (TextView) findViewById(R.id.answer);

        mShowButton = (Button) findViewById(R.id.showCheat_utton);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(String.valueOf(mAnswer));
                mIsCheated = true;
            }
        });

    }

    // for back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("IS_CHEATED", mIsCheated);
        setResult(RESULT_OK, intent);
        finish();
    }
}
