package de.hwr_berlin.quick_e_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

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

        ArrayList<Highscore> arrayofScores = new ArrayList<>();
        HighscoreAdapter adapter = new HighscoreAdapter(this, arrayofScores);

        Highscore newScores = new Highscore("Hans", 1111);
        adapter.add(newScores);
        //TODO Datensätze von Server auslesen und in Liste eintragen

        ListView items = (ListView) this.findViewById(R.id.lvHighscore);
        items.setAdapter(adapter);

    }
    @Override
    public void onBackPressed()
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
