package android.dev.personalassistant.enums;

/**
 * Created by saurabh on 6/2/18.
 */

public enum CardType {


    Credit("Credit Card"),
    Debit("Debit Card"),
    CreditCumDebit("Credit cum Debit card");

    private String value;

    CardType(String value){
        this.value=value;
    };

    @Override
    public String toString() {
        return value;
    }


}
