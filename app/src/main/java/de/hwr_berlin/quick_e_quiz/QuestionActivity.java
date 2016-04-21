package de.hwr_berlin.quick_e_quiz;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Category;
import de.hwr_berlin.quick_e_quiz.db.Question;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    List<Category> openCategpries;
    TextView tvQuestions;
    Question currentQuestion;
    Category currentCategory;
    static int faultMultiplier = 25;
    long timer = 0;
    int time = 0;
    int wrongAnswers = 0;
    int score = 0;
    TextView timerTextView;
    TextView faultCountTextView;
    private MediaPlayer mpNein;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - timer;
            time = (int) (millis / 1000);
            timerTextView.setText("Zeit: " + String.format("%d", time));
            faultCountTextView.setText(String.format("Fehler: " + "%d", wrongAnswers));


            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        tvQuestions = (TextView) findViewById(R.id.tvQuestions);
        findViewById(R.id.btnAnswer1).setOnClickListener(this);
        findViewById(R.id.btnAnswer2).setOnClickListener(this);
        findViewById(R.id.btnAnswer3).setOnClickListener(this);
        findViewById(R.id.btnAnswer4).setOnClickListener(this);
        timer = System.currentTimeMillis();
        score = 0;
        timerHandler.postDelayed(timerRunnable, 0);
        timerTextView = (TextView) findViewById(R.id.tvTimer);
        faultCountTextView =  (TextView) findViewById(R.id.tvFaultCounter);

        openCategpries = Category.find(Category.class, "cid != ?", "0");
        Collections.shuffle(openCategpries);
        currentCategory = openCategpries.remove(0);

        Question start = randomQuestion();
        setQuestionValues(start);

        mpNein = MediaPlayer.create(this, R.raw.nein);
    }

    private String getCategorie(int cId) {
        List<Category> categories = Category.find(Category.class, "cId = ?", String.valueOf(cId));
        String output ="";

        for (Category categorie : categories) {
            output += categorie.getName() + "\n";
        }
        return output;
    }

    private void setQuestionValues(Question question){
        ((TextView) findViewById(R.id.tvQuestions)).setText(question.getQuestion());
        ((TextView) findViewById(R.id.tvCategory)).setText("Kategorie: " + getCategorie(question.getCategory()));

        List<String> options = new ArrayList<>();
        options.add(question.getCorrect());
        options.add(question.getWrong1());
        options.add(question.getWrong2());
        options.add(question.getWrong3());
        Collections.shuffle(options);
        ((Button) findViewById(R.id.btnAnswer1)).setText(options.get(0));
        ((Button) findViewById(R.id.btnAnswer2)).setText(options.get(1));
        ((Button) findViewById(R.id.btnAnswer3)).setText(options.get(2));
        ((Button) findViewById(R.id.btnAnswer4)).setText(options.get(3));

        currentQuestion = question;
    }

    private void highscore(){
        timerHandler.removeCallbacks(timerRunnable);
        score = time + wrongAnswers * faultMultiplier;
        Toast.makeText(this, "Dein Score: " + score, Toast.LENGTH_SHORT).show();

        CustomDialogClass cdd = new CustomDialogClass(QuestionActivity.this);
        cdd.show();
        //Dirty, aber implementierung in der CustomDialogClass crasht sonst.
        cdd.tv_time.append(Integer.toString(time));
        cdd.tv_fault.append(Integer.toString(wrongAnswers * faultMultiplier));
        cdd.tv_score.append(Integer.toString(score));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAnswer1:
            case R.id.btnAnswer2:
            case R.id.btnAnswer3:
            case R.id.btnAnswer4:
                Button button = (Button) v;
                Question next;
                if (button.getText() == currentQuestion.getCorrect()){
                    Toast.makeText(this, "Richtig!!!", Toast.LENGTH_SHORT).show();
                    if (openCategpries.size() > 0){
                        currentCategory = openCategpries.remove(0);
                        next = randomQuestion();
                        setQuestionValues(next);
                    }
                    else{
                        highscore();
                    }
                }
                else{
                    mpNein.start();
                    wrongAnswers++;
                    next = randomQuestion();
                    setQuestionValues(next);
                }
                break;
        }
    }

    private Question randomQuestion() {
        List<Question> questions = Question.find(Question.class, " category = ?", String.valueOf(currentCategory.getCid()));
        return questions.get((int)(Math.random()*questions.size()));
    }

    public int getScore() {
        return score;
    }
}
