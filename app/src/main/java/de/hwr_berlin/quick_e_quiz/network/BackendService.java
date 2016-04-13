package de.hwr_berlin.quick_e_quiz.network;

import java.util.List;

import de.hwr_berlin.quick_e_quiz.db.Category;
import de.hwr_berlin.quick_e_quiz.db.Highscore;
import de.hwr_berlin.quick_e_quiz.db.Question;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BackendService {
    @GET("?questions")
    Call<List<Question>> listQuestions();

    @GET("?categories")
    Call<List<Category>> listCategories();

    @GET("?highscore")
    Call<List<Highscore>> listHighscores();

    @FormUrlEncoded
    @POST("CSDB3/")
    Call<ResponseBody> postScore(@Field("name") String name, @Field("score") int score);
}
