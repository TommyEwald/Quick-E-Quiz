package de.hwr_berlin.quick_e_quiz.db;

import com.orm.SugarRecord;

public class Question extends SugarRecord {
    int qid;
    int category;
    String question;
    String correct;
    String wrong1;
    String wrong2;
    String wrong3;

    public Question() {
    }

    public Question(int qid, int category, String question, String correct, String wrong1, String wrong2, String wrong3) {
        this.qid = qid;
        this.category = category;
        this.question = question;
        this.correct = correct;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correct;
    }

    public String getWrong1() {
        return wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public String getWrong3() {
        return wrong3;
    }

    public int getCategory() {
        return category;
    }
}
