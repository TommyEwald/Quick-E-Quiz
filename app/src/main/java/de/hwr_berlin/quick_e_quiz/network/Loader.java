package de.hwr_berlin.quick_e_quiz.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Category;
import de.hwr_berlin.quick_e_quiz.db.Highscore;
import de.hwr_berlin.quick_e_quiz.db.Question;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Loader {
    public static void loadData(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://lamp.wlan.hwr-berlin.de/CS/CSDB3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BackendService service = retrofit.create(BackendService.class);
        service.listQuestions().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                Question.deleteAll(Question.class);
                List<Question> questions = response.body();
                for (Question question : questions) {
                    question.save();
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(context, "Fragen konnten nicht geladen werden.", Toast.LENGTH_SHORT).show();
            }
        });

        service.listCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Category.deleteAll(Category.class);
                for (Category category : response.body()) {
                    category.save();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(context, "Kategorien konnten nicht geladen werden.", Toast.LENGTH_SHORT).show();
            }
        });

        service.listHighscores().enqueue(new Callback<List<Highscore>>() {
            @Override
            public void onResponse(Call<List<Highscore>> call, Response<List<Highscore>> response) {
                Highscore.deleteAll(Highscore.class);
                for (Highscore highscore : response.body()) {
                    highscore.save();
                }
            }

            @Override
            public void onFailure(Call<List<Highscore>> call, Throwable t) {
                Toast.makeText(context, "Highscore konnte nicht geladen werden.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void insertScore(final Context context, String name, int score) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://lamp.wlan.hwr-berlin.de/CS/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BackendService service = retrofit.create(BackendService.class);
        service.postScore(name, score).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("LOADER", "post worked");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("LOADER", "post fail");
                Toast.makeText(context, "Highscore konnte nicht eingetragen werden.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
