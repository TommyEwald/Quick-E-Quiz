package de.hwr_berlin.quick_e_quiz;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Highscore;
import de.hwr_berlin.quick_e_quiz.db.HighscoreAdapter;
import de.hwr_berlin.quick_e_quiz.network.Loader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HighscoreActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.highscore);
        getSupportActionBar().setTitle("");

        showHighscores();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadHighscore();
            }
        });


        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        reloadHighscore();
    }


    @Override
    public void onBackPressed()
    {
        finish();
    }

    private void showHighscores() {
        List<Highscore> highscores = Highscore.listAll(Highscore.class);
        HighscoreAdapter adapter = new HighscoreAdapter(this, highscores);

        ListView items = (ListView) this.findViewById(R.id.lvHighscore);
        items.setAdapter(adapter);
    }

    private void reloadHighscore() {
        Loader.loadHighscore().enqueue(new Callback<List<Highscore>>() {
            @Override
            public void onResponse(Call<List<Highscore>> call, Response<List<Highscore>> response) {
                Highscore.deleteAll(Highscore.class);
                for (Highscore highscore : response.body()) {
                    highscore.save();
                }

                showHighscores();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Highscore>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Highscores konnten nicht geladen werden", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
