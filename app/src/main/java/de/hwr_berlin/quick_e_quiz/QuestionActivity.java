package de.hwr_berlin.quick_e_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Category;
import de.hwr_berlin.quick_e_quiz.db.Question;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvQuestions;
    String checkCorrect;

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
        findViewById(R.id.btnAnswer1).setOnClickListener(this);
        findViewById(R.id.btnAnswer2).setOnClickListener(this);
        findViewById(R.id.btnAnswer3).setOnClickListener(this);
        findViewById(R.id.btnAnswer4).setOnClickListener(this);
        getCategories();
        Question start = randomQuestion(0);
        setQuestionValues(start);

    }

    private List<Category> getCategories() {
        List<Category> categories = Category.listAll(Category.class);
        return Category.listAll(Category.class);
    }

    private void setQuestionValues(Question question){
        ((TextView) findViewById(R.id.tvQuestions)).setText(question.getQuestion());
        ((Button) findViewById(R.id.btnAnswer1)).setText(question.getCorrect());
        ((Button) findViewById(R.id.btnAnswer2)).setText(question.getWrong1());
        ((Button) findViewById(R.id.btnAnswer3)).setText(question.getWrong2());
        ((Button) findViewById(R.id.btnAnswer4)).setText(question.getWrong3());
        checkCorrect = question.getCorrect();
    }

    private void showQuestions() {
        List<Question> questions = Question.listAll(Question.class);
        String output = "";
        for (Question question : questions) {
            output += question.getId() + " - " + question.getQuestion() + "\n";
        }
        tvQuestions.setText(output);
    }

    private void showQuestion(int category) {
        List<Question> questions = Question.find(Question.class, " category = ?", String.valueOf(category));
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
                Question q = randomQuestion(0);
                Toast.makeText(this, String.valueOf(q.getId()) + " - " + q.getQuestion(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnAnswer1:
            case R.id.btnAnswer2:
            case R.id.btnAnswer3:
            case R.id.btnAnswer4:
                Button button = (Button) v;
                if (button.getText() == checkCorrect){
                    Toast.makeText(this, "Richtig!!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private Question randomQuestion(int category) {
        List<Question> questions = Question.find(Question.class, " category = ?", String.valueOf(category));
        return questions.get((int)(Math.random()*questions.size()));
    }
}
