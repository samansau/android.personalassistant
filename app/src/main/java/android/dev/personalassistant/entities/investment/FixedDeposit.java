package android.dev.personalassistant.entities.investment;

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

//@Entity(foreignKeys = {@ForeignKey(entity= BankAccount.class,
//        parentColumns = "bankAccountId",
//        childColumns ="bankAccountId"
//)
//},
//        indices = {@Index(value =
//                {"fdNumber"}, unique = true)}
//
//)
public class FixedDeposit {
//    @PrimaryKey
//    @NonNull
//    private int fdId;
//    private int bankAccountId;
//    private String fdStatus;
//    private String fdComment;
//    private Date fdInceptionDate;
//    private Date fdMaturityDate;
//    private float interest;
//    private float principleAmount;
//    private float maturityAmount;
//
//    @NonNull
//    public int getFdId() {
//        return fdId;
//    }
//
//
//    public void setFdId(@NonNull int fdId) {
//        this.fdId = fdId;
//    }
//
//    public int getBankAccountId() {
//        return bankAccountId;
//    }
//
//    public void setBankAccountId(int bankAccountId) {
//        this.bankAccountId = bankAccountId;
//    }
//
//    public String getFdStatus() {
//        return fdStatus;
//    }
//
//    public void setFdStatus(String fdStatus) {
//        this.fdStatus = fdStatus;
//    }
//
//    public String getFdComment() {
//        return fdComment;
//    }
//
//    public void setFdComment(String fdComment) {
//        this.fdComment = fdComment;
//    }
//
//    public Date getFdInceptionDate() {
//        return fdInceptionDate;
//    }
//
//    public void setFdInceptionDate(Date fdInceptionDate) {
//        this.fdInceptionDate = fdInceptionDate;
//    }
//
//    public Date getFdMaturityDate() {
//        return fdMaturityDate;
//    }
//
//    public void setFdMaturityDate(Date fdMaturityDate) {
//        this.fdMaturityDate = fdMaturityDate;
//    }
//
//    public float getInterest() {
//        return interest;
//    }
//
//    public void setInterest(float interest) {
//        this.interest = interest;
//    }
//
//    public float getPrincipleAmount() {
//        return principleAmount;
//    }
//
//    public void setPrincipleAmount(float principleAmount) {
//        this.principleAmount = principleAmount;
//    }
//
//    public float getMaturityAmount() {
//        return maturityAmount;
//    }
//
//    public void setMaturityAmount(float maturityAmount) {
//        this.maturityAmount = maturityAmount;
//    }
//
//
//    @Override
//    public String toString() {
//        return "FixedDeposit{" +
//                "fdId=" + fdId +
//                ", bankAccountId=" + bankAccountId +
//                ", fdStatus='" + fdStatus + '\'' +
//                ", fdComment='" + fdComment + '\'' +
//                ", fdInceptionDate=" + fdInceptionDate +
//                ", fdMaturityDate=" + fdMaturityDate +
//                ", interest=" + interest +
//                ", principleAmount=" + principleAmount +
//                ", maturityAmount=" + maturityAmount +
//                '}';
//    }
}
