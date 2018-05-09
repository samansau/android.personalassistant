package android.dev.personalassistant.entities;

import android.dev.personalassistant.enums.Relation;

import java.util.Date;

/**
 * Created by saurabh on 5/8/18.
 */

public class Person {
    public int id;
    public String fullName;
    public Relation relation;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
