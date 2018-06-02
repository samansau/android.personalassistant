package android.dev.personalassistant.vo;

/**
 * Created by saurabh on 6/2/18.
 */

public class CardVO {

    public int cardId;
    private String cardTypeValue;
    private String bankNameValue;
    private String branchValue;
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

    public String getBankNameValue() {
        return bankNameValue;
    }

    public void setBankNameValue(String bankNameValue) {
        this.bankNameValue = bankNameValue;
    }

    public String getBranchValue() {
        return branchValue;
    }

    public void setBranchValue(String branchValue) {
        this.branchValue = branchValue;
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
}
