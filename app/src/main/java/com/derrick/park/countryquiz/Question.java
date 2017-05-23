package com.derrick.park.countryquiz;

/**
 * Created by MinaFujisawa on 2017/05/19.
 */

public class Question {
    private int question;

    public Question(int questionColor) {
        this.question = questionColor;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int questionColor) {
        this.question = questionColor;
    }

}
