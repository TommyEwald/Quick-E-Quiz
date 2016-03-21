package de.hwr_berlin.quick_e_quiz.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by EwaldT on 21.03.2016.
 */
public class MockHighscore {
    public String rank;
    public String name;
    public String score;

    public MockHighscore(String rank, String name, String score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public MockHighscore(JSONObject object){
        try {
            this.rank = object.getString("rank");
            this.name = object.getString("name");
            this.score = object.getString("score");

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<MockHighscore> fromJson(JSONArray jsonObjects){
        ArrayList<MockHighscore> scores = new ArrayList<MockHighscore>();
        for (int i = 0; i < jsonObjects.length(); i++){
            try{
                scores.add(new MockHighscore(jsonObjects.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return scores;
    }

}
