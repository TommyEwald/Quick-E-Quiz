package de.hwr_berlin.quick_e_quiz.db;

import com.orm.SugarRecord;

public class Highscore extends SugarRecord {
    String name;
    int score;

    public Highscore() {
    }

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
