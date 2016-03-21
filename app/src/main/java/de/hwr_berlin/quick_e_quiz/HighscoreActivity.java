package de.hwr_berlin.quick_e_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hwr_berlin.quick_e_quiz.db.HighscoreAdapter;
import de.hwr_berlin.quick_e_quiz.db.MockHighscore;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.highscore);

        ArrayList<MockHighscore> arrayofScores = new ArrayList<MockHighscore>();
        HighscoreAdapter adapter = new HighscoreAdapter(this, arrayofScores);

        MockHighscore newScores = new MockHighscore("1", "Hans", "1111");
        adapter.add(newScores);

        ListView items = (ListView) this.findViewById(R.id.lvHighscore);
        items.setAdapter(adapter);


    }

}
