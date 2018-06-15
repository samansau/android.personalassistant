package android.dev.personalassistant.vo.investment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Person;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

/**
 * Created by saurabh on 6/10/18.
 */


public class FixedDepositVO {

    private int fdId;
    private int bankAccountId;
    private String bankName;

    private String fdStatus;
    private String fdComments;
    private List<String> personNames;
    private Date fdInceptionDate;
    private Date fdMaturityDate;
    private float interest;
    private float principleAmount;
    private float maturityAmount;

    @NonNull
    public int getFdId() {
        return fdId;
    }

    public void setFdId(@NonNull int fdId) {
        this.fdId = fdId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFdStatus() {
        return fdStatus;
    }

    public void setFdStatus(String fdStatus) {
        this.fdStatus = fdStatus;
    }

    public String getFdComments() {
        return fdComments;
    }

    public void setFdComments(String fdComments) {
        this.fdComments = fdComments;
    }

    public List<String> getPersonNames() {
        return personNames;
    }

    public void setPersonNames(List<String> personNames) {
        this.personNames = personNames;
    }

    public List<String> getPersons() {
        return personNames;
    }

    public void setPersons(List<String> personNames) {
        this.personNames = personNames;
    }

    public Date getFdInceptionDate() {
        return fdInceptionDate;
    }

    public void setFdInceptionDate(Date fdInceptionDate) {
        this.fdInceptionDate = fdInceptionDate;
    }

    public Date getFdMaturityDate() {
        return fdMaturityDate;
    }

    public void setFdMaturityDate(Date fdMaturityDate) {
        this.fdMaturityDate = fdMaturityDate;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(float principleAmount) {
        this.principleAmount = principleAmount;
    }

    public float getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(float maturityAmount) {
        this.maturityAmount = maturityAmount;
    }
}
