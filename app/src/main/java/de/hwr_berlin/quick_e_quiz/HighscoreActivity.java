package de.hwr_berlin.quick_e_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Highscore;
import de.hwr_berlin.quick_e_quiz.db.HighscoreAdapter;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.highscore);

        List<Highscore> highscores = Highscore.listAll(Highscore.class);
        HighscoreAdapter adapter = new HighscoreAdapter(this, highscores);

        ListView items = (ListView) this.findViewById(R.id.lvHighscore);
        items.setAdapter(adapter);
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
