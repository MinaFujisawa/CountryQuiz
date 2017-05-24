package com.derrick.park.countryquiz;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private final String TAG = "QuizActivity";
    private Button mTureButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQustionText;
    private int mCurrentIndex = 0;
    private int mPoint = 0;
    private static final String KEY_INDEX = "Index";

    private Question[] questionList = {
            new Question(R.string.q_canada, false),
            new Question(R.string.q_france, true),
            new Question(R.string.q_japan, true),
            new Question(R.string.q_usa, false),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate()");
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        //first question
        mQustionText = (TextView) findViewById(R.id.question_text);
        mQustionText.setText(questionList[0].getTextResId());


        mTureButton = (Button) findViewById(R.id.true_button);
        mTureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // anything you want when the button is pressed
                check(true);
                updateBtn(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(false);
                updateBtn(true);

            }

        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex++;
                if (mCurrentIndex == questionList.length) {
                    showResult();
                }
                mCurrentIndex = mCurrentIndex % (questionList.length);
                mQustionText = (TextView) findViewById(R.id.question_text);
                mQustionText.setText(questionList[mCurrentIndex].getTextResId());
                updateBtn(false);
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = questionList.length - 1;
                } else {
                    mCurrentIndex--;
                }

                mQustionText = (TextView) findViewById(R.id.question_text);
                mQustionText.setText(questionList[mCurrentIndex].getTextResId());
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(TAG,"onStart()");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(TAG,"onResumed()");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG,"onResumed()");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(TAG,"onStop()");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG,"onDestroy()");
//    }

    private void check(boolean userAnswer) {
        if (questionList[mCurrentIndex].isAnswerTrue() == userAnswer) {
            Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mPoint++;
        } else {
            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBtn(boolean answered) {
        mTureButton.setEnabled(!answered);
        mFalseButton.setEnabled(!answered);
    }

    private void showResult() {
        double score = mPoint / (double) questionList.length * 100;
        Toast.makeText(QuizActivity.this, String.valueOf(score) + "%", Toast.LENGTH_SHORT).show();
    }

}
