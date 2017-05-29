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
    private TextView mCheatingChanceTextView;
    private boolean mAnswer;
    private boolean mIsCheater = false;
    private boolean mIsShowing = false;
    private int numCheatingChance = 5;
    private static final String IS_CHEATER = "com.derrick.park.countryquiz.is_cheated"; //should be very unique
    private static final String KEY = "key";
    private static final String TAG = "CheatActivity";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // for changing orientation
        if (savedInstanceState != null) {
            mIsShowing = savedInstanceState.getBoolean(KEY, false);
        }

        mCheatingChanceTextView = (TextView) findViewById(R.id.cheatingChance);
        mCheatingChanceTextView.setText(String.valueOf(numCheatingChance));


        //for the button
        mShowButton = (Button) findViewById(R.id.showCheat_utton);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = true;
                mAnswerTextView.setText(String.valueOf(mAnswer));
                mIsShowing = true;
                numCheatingChance--;
                mCheatingChanceTextView.setText(String.valueOf(numCheatingChance));
                if(numCheatingChance == 0){
                    mShowButton.setEnabled(false);
                }

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                }
            }
        });



        mAnswer = getIntent().getBooleanExtra("answer", false);
        mAnswerTextView = (TextView) findViewById(R.id.answer);

        mAPILevelTextView = (TextView) findViewById(R.id.apiLevel);
        mAPILevelTextView.setText(String.valueOf(android.os.Build.VERSION.SDK));

        // for changing orientation
        if(mIsShowing){
            mAnswerTextView.setText(String.valueOf(mAnswer));
            mIsCheater = true;
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
        intent.putExtra(IS_CHEATER, mIsCheater);
        setResult(RESULT_OK, intent);
        finish();
    }

}
