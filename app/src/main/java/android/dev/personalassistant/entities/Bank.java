package android.dev.personalassistant.entities;

/**
 * Created by saurabh on 5/8/18.
 */

public class Bank {
    public int id;
    public String bankName;
    public String branch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
