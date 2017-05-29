package com.derrick.park.countryquiz;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
    private Button mShowButton;
    private TextView mAnswerTextView;
    private TextView mAPILevelTextView;
    private boolean mAnswer;
    private boolean mIsCheatr = false;
    private boolean mIsShowing = false;
    private static final String IS_CHEATER = "com.derrick.park.countryquiz.is_cheated"; //should be very unique
    private static final String KEY = "key";
    private final String TAG = "CheatActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            mIsShowing = savedInstanceState.getBoolean(KEY, false);
        }

        mAnswer = getIntent().getBooleanExtra("answer", false);

        mAnswerTextView = (TextView) findViewById(R.id.answer);

        mAPILevelTextView = (TextView) findViewById(R.id.apiLevel);
        mAPILevelTextView.setText(String.valueOf(android.os.Build.VERSION.SDK));

        //for the button
        mShowButton = (Button) findViewById(R.id.showCheat_utton);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheatr = true;
                mAnswerTextView.setText(String.valueOf(mAnswer));
                mIsShowing = true;

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                }
            }
        });

        // for changing orientation
        if(mIsShowing){
            mAnswerTextView.setText(String.valueOf(mAnswer));
            mIsCheatr = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY, mIsShowing);
    }

    // for back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(IS_CHEATER, mIsCheatr);
        setResult(RESULT_OK, intent);
        finish();
    }

}
