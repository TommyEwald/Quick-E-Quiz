package de.hwr_berlin.quick_e_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Question;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        tvQuestions = (TextView) findViewById(R.id.tvQuestions);
        findViewById(R.id.btnNewQuestion).setOnClickListener(this);
        findViewById(R.id.btnRandomQuestion).setOnClickListener(this);
        showQuestions();
    }

    private void showQuestions() {
        List<Question> questions = Question.listAll(Question.class);
        String output = "";
        for (Question question : questions) {
            output += question.getId() + " - " + question.getQuestion() + "\n";
        }
        tvQuestions.setText(output);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewQuestion:
                Question question = new Question(1, 1, "Wie geht es dir?", "richtig", "falsch", "falsch", "falsche");
                question.save();
                showQuestions();
                break;
            case R.id.btnRandomQuestion:
                Question q = randomQuestion();
                Toast.makeText(this, String.valueOf(q.getId()) + " - " + q.getQuestion(), Toast.LENGTH_SHORT).show();
        }
    }

    private Question randomQuestion() {
        return Question.find(Question.class, null, null, null, "Random()", "1").get(0);
    }
}
