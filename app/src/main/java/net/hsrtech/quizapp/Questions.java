package net.hsrtech.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {

    private RadioButton radioMale;
    private RadioButton radioFeMale;

    private CheckBox checkbox1;
    private CheckBox checkbox2;

    private TextView question3;
    private TextView question4;
    private TextView score;

    private TextView scoreHead;

    private ScrollView scrollView;

    private Toast toast;

    private ArrayList<String> errors;

    private String[] error_values;

    private int scroll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        this.radioMale = (RadioButton) findViewById(R.id.radioMale);
        this.radioFeMale = (RadioButton) findViewById(R.id.radioFemale);

        this.checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        this.checkbox2 = (CheckBox) findViewById(R.id.checkbox2);

        this.question3 = (TextView) findViewById(R.id.question3answer);
        this.question4 = (TextView) findViewById(R.id.question4answer);

        this.scoreHead = (TextView) findViewById(R.id.scoreHead);

        this.scrollView = (ScrollView) findViewById(R.id.scrollView);

        this.error_values = getResources().getStringArray(R.array.errors);
        this.score = ((TextView) findViewById(R.id.score));
    }

    public void submit(View view) {
        this.errors = new ArrayList<>();
        this.score.setVisibility(View.GONE);
        this.scoreHead.setVisibility(View.GONE);

        String answer2 = this.getAnswer1();
        String answer3 = this.getAnswer2();
        String answer1 = this.getAnswer3();
        int answer4 = this.getAnswer4();

        TextView scroll = (TextView) findViewById(R.id.scoreHead);

        if (this.errors.size() > 0) {
            String errors = "";

            for (String error : this.errors) {
                errors = errors + "\n" + error;
            }

            int id;
            switch (this.scroll) {
                case 1:
                    id = R.id.question1;
                    break;
                case 2:
                    id = R.id.question2;
                    break;
                case 3:
                    id = R.id.question3;
                    break;
                case 4:
                    id = R.id.question4answer;
                    break;
                default:
                    id = R.id.question1;
            }

            scroll = (TextView) findViewById(id);

            this.scrollView.scrollTo(0, scroll.getBottom());
            this.toast(errors);

            return;
        }

        this.score.setText(getResources().getString(R.string.score, answer1, answer2, answer3, answer4));
        this.scoreHead.setVisibility(View.VISIBLE);
        this.score.setVisibility(View.VISIBLE);
        this.scrollView.scrollTo(0, scroll.getTop());
    }


    public String getAnswer1() {
        String gender;
        if (this.radioMale.isChecked()) {
            gender = "Male";
        } else if (this.radioFeMale.isChecked()) {
            gender = "Female";
        } else {
            this.addError(this.error_values[0]);
            this.scroll = 1;
            return null;
        }
        return gender;
    }

    public String getAnswer2() {
        String temp = "";
        boolean checked = false;

        if (this.checkbox1.isChecked()) {
            temp = "Male";
            checked = true;
        }
        if (this.checkbox2.isChecked()) {
            temp = temp.length() > 0 ? temp + ", Female" : "Female";
            checked = true;
        }
        if (!checked) {
            this.addError(this.error_values[1]);
            this.scroll = 2;
            return null;
        }

        return temp;
    }

    public String getAnswer3() {
        String answer = this.question3.getText().toString();
        if (answer.length() == 0) {
            this.addError(this.error_values[2]);
            this.scroll = 3;
            return null;
        }

        return answer;
    }

    public int getAnswer4() {
        int correctAnswer = 3;
        String text = this.question4.getText().toString();
        if (text.length() <= 0) {
            this.addError(this.error_values[3]);
            this.scroll = 4;
            return 0;
        }
        int val = Integer.parseInt(text);

        if (correctAnswer != val) {
            this.addError(this.error_values[3]);
            this.scroll = 3;
        }

        return val;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public void toast(String message) {
        if (this.toast != null)
            this.toast.cancel();

        this.toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        this.toast.show();
    }

}
