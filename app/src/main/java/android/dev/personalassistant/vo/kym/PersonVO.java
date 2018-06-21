package android.dev.personalassistant.vo.kym;

import android.dev.personalassistant.enums.Relations;

/**
 * Created by saurabh on 6/2/18.
 */

public class PersonVO {

    private boolean isNew;
    private int personId;
    private String fullName;
    private String relation;
    private String dob;
    private String panCardNumber;
    private String aadharCardNumber;
    private String passportNumber;
    private String passportExpiry;
    private String drivingLisenceNumber;
    private String drivingLisenceExpiry;
    private String expenseTag;
    private int expenseTagId;



    public boolean isNew() {
        return isNew;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
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

    public String getExpenseTag() {
        return expenseTag;
    }

    public void setExpenseTag(String expenseTag) {
        this.expenseTag = expenseTag;
    }

    public int getExpenseTagId() {
        return expenseTagId;
    }

    public void setExpenseTagId(int expenseTagId) {
        this.expenseTagId = expenseTagId;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "fullName='" + fullName + '\'' +
                ", relation='" + relation + '\'' +
                ", dob='" + dob + '\'' +
                ", panCardNumber='" + panCardNumber + '\'' +
                ", aadharCardNumber='" + aadharCardNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportExpiry='" + passportExpiry + '\'' +
                ", drivingLisenceNumber='" + drivingLisenceNumber + '\'' +
                ", drivingLisenceExpiry='" + drivingLisenceExpiry + '\'' +
                '}';
    }
}
