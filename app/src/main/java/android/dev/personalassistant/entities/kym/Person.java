package android.dev.personalassistant.entities.kym;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.dev.personalassistant.enums.Relations;
import android.support.annotation.NonNull;

/**
 * Created by saurabh on 5/8/18.
 */

@Entity(foreignKeys = {@ForeignKey(entity = ExpenseTag.class,
                                            parentColumns = "tagId",
                                            childColumns = "tagId")

                        },


        indices = @Index(value="fullName",unique = true))
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int personId;
    @NonNull
    private String fullName;
    private String relation;
    private String dob;
    private String panCardNumber;
    private String aadharCardNumber;
    private String passportNumber;
    private String passportExpiry;
    private String drivingLisenceNumber;
    private String drivingLisenceExpiry;
    private int tagId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getDrivingLisenceNumber() {
        return drivingLisenceNumber;
    }

    public void setDrivingLisenceNumber(String drivingLisenceNumber) {
        this.drivingLisenceNumber = drivingLisenceNumber;
    }

    public String getDrivingLisenceExpiry() {
        return drivingLisenceExpiry;
    }

    public void setDrivingLisenceExpiry(String drivingLisenceExpiry) {
        this.drivingLisenceExpiry = drivingLisenceExpiry;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
