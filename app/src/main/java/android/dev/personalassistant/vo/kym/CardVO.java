package android.dev.personalassistant.vo.kym;

/**
 * Created by saurabh on 6/2/18.
 */

public class CardVO {

    public int cardId;
    private String cardTypeValue;
    private int bankAccountId;

    private String bankName;
    private String branch;

    private String cardCategoryValue;
    private String cardNumberValue;
    private String cardExpiryDateValue;
    private String cardCvvValue;


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardTypeValue() {
        return cardTypeValue;
    }

    public void setCardTypeValue(String cardTypeValue) {
        this.cardTypeValue = cardTypeValue;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCardExpiryDateValue() {
        return cardExpiryDateValue;
    }

    public void setCardExpiryDateValue(String cardExpiryDateValue) {
        this.cardExpiryDateValue = cardExpiryDateValue;
    }

    public String getCardCategoryValue() {
        return cardCategoryValue;
    }

    public void setCardCategoryValue(String cardCategoryValue) {
        this.cardCategoryValue = cardCategoryValue;
    }

    public String getCardNumberValue() {
        return cardNumberValue;
    }

    public void setCardNumberValue(String cardNumberValue) {
        this.cardNumberValue = cardNumberValue;
    }


    public String getCardCvvValue() {
        return cardCvvValue;
    }

    public void setCardCvvValue(String cardCvvValue) {
        this.cardCvvValue = cardCvvValue;
    }


    @Override
    public String toString() {
        return "CardVO{" +
                "cardId=" + cardId +
                ", cardTypeValue='" + cardTypeValue + '\'' +
                ", bankAccountId=" + bankAccountId +
                ", cardCategoryValue='" + cardCategoryValue + '\'' +
                ", cardNumberValue='" + cardNumberValue + '\'' +
                ", cardExpiryDateValue='" + cardExpiryDateValue + '\'' +
                ", cardCvvValue='" + cardCvvValue + '\'' +
                '}';
    }
}
