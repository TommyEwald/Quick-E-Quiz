package de.hwr_berlin.quick_e_quiz.db;

import com.orm.SugarRecord;

public class Category extends SugarRecord {
    int cid;
    String name;

    public Category() {}

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }
}
