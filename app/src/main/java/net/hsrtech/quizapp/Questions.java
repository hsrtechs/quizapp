package net.hsrtech.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Questions extends AppCompatActivity {

    private String[] questions;
    private int questionNumber = 0;
    private int score = 0;

    private TextView questionTextView;
    private TextView scoreTextView;

    private boolean[] answers;
    private boolean[] correctAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        this.questionTextView = (TextView) findViewById(R.id.question);
        this.scoreTextView = (TextView) findViewById(R.id.score);

        this.answers = new boolean[10];

        this.setQuestions();
        this.askNextQuestion();
        this.setScore();
    }

    public boolean askNextQuestion() {
        if (this.questions.length > this.questionNumber) {
            this.questionNumber++;

            if (this.questionTextView != null)
                this.questionTextView.setText(this.getQuestion());

            return true;
        }
        return false;

    }

    public void setScore() {
        if (this.score < 0) {
            this.score = 0;
        }
        if (this.scoreTextView != null) {
            this.scoreTextView.setText(getString(R.string.score, this.score));
        } else {
            Log.d("Score Failed", "Score: " + this.score);
        }
    }

    public void submitAnswer(View view) {
        this.answers[this.getQuestionNumber()] = view.getId() == R.id.yes;

        Log.d("submit", this.getQuestionNumber() + ": " + this.getAnswer() + " " + this.getCorrectAnswer());

        if (this.checkAnswers(this.getAnswer())) {
            this.score++;
        }
        this.askNextQuestion();
        this.setScore();

    }

    public void previous(View view) {
        if (this.getQuestionNumber() >= 0) {
            this.questionNumber -= this.getQuestionNumber() == 0 ? 1 : 2;

            this.askNextQuestion();

            if (this.getAnswer() == this.getCorrectAnswer()) {
                this.score--;
            }

            this.setScore();

        }
    }

    public String getQuestion() {
        return this.questions[this.getQuestionNumber()] + " ?";
    }

    public void setQuestions() {
        this.questions = new String[10];
        this.questions[0] = "This is the first questions";
        this.questions[1] = "This is the second questions";
        this.questions[2] = "This is the third questions";
        this.questions[3] = "This is the forth questions";
        this.questions[4] = "This is the fifth questions";
        this.questions[5] = "This is the sixth questions";
        this.questions[6] = "This is the seventh questions";
        this.questions[7] = "This is the eighth questions";
        this.questions[8] = "This is the ninth questions";
        this.questions[9] = "This is the tenth questions";

        this.setCorrectAnswers();
    }

    public boolean checkAnswers(boolean answer) {
        return this.correctAnswers[this.getQuestionNumber()] == answer;
    }

    public int getQuestionNumber() {
        return this.questionNumber - 1;
    }

    public void setCorrectAnswers() {
        this.correctAnswers = new boolean[10];

        this.correctAnswers[0] = true;
        this.correctAnswers[1] = true;
        this.correctAnswers[2] = false;
        this.correctAnswers[3] = true;
        this.correctAnswers[4] = false;
        this.correctAnswers[5] = true;
        this.correctAnswers[6] = true;
        this.correctAnswers[7] = false;
        this.correctAnswers[8] = true;
        this.correctAnswers[9] = false;
    }

    public boolean getAnswer() {
        return this.answers[this.getQuestionNumber()];
    }

    public boolean getAnswer(int question) {
        return this.answers[question - 1];
    }

    public boolean getCorrectAnswer() {
        return this.correctAnswers[this.getQuestionNumber()];
    }

    public boolean getCorrectAnswer(int question) {
        return this.correctAnswers[question - 1];
    }
}
