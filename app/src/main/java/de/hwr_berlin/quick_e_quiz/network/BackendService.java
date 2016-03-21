package de.hwr_berlin.quick_e_quiz.network;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Category;
import de.hwr_berlin.quick_e_quiz.db.Question;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BackendService {
    @GET("questions.json")
    Call<List<Question>> listQuestions();

    @GET("categories.json")
    Call<List<Category>> listCategories();
}
