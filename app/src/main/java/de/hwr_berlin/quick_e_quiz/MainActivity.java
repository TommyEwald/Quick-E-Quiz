package de.hwr_berlin.quick_e_quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import de.hwr_berlin.quick_e_quiz.network.Loader;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartQuiz).setOnClickListener(this);
        findViewById(R.id.btnHighscore).setOnClickListener(this);
        findViewById(R.id.btnRules).setOnClickListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        Loader.loadData(this);

        mp = MediaPlayer.create(this, R.raw.jeopardy);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "Starte Quiz",Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.btnStartQuiz:
                Intent startQuizIntent = new Intent(this, QuestionActivity.class);
                startActivity(startQuizIntent);
                break;
            case R.id.btnHighscore:
                Intent highscoreIntent = new Intent(this, HighscoreActivity.class);
                startActivity(highscoreIntent);
                break;
            case R.id.btnRules:
                Intent rulesIntent = new Intent(this, RulesActivity.class);
                startActivity(rulesIntent);
                break;
        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
