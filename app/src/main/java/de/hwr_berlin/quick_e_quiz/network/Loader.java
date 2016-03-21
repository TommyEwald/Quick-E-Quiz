package de.hwr_berlin.quick_e_quiz.network;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Loader implements Callback<List<Question>> {
    private Context context;

    public Loader(Context context) {
        this.context = context;
    }

    public void loadQuestions() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/PascalHelbig/cfba6711294e68f07f1b/raw/27c8d640f8b0e6ee593a4dee8d466d9b60599885/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BackendService service = retrofit.create(BackendService.class);
        service.listQuestions().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
        Question.deleteAll(Question.class);
        List<Question> questions = response.body();
        for (Question question : questions) {
            Question.save(question);
        }
    }

    @Override
    public void onFailure(Call<List<Question>> call, Throwable t) {
        Toast.makeText(context, "Fragen konnten nicht geladen werden.", Toast.LENGTH_SHORT).show();
    }
}
