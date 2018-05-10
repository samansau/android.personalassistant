package android.dev.personalassistant.message.content;

import android.dev.personalassistant.message.enums.MessageActionsEnum;

/**
 * Created by saurabh on 5/10/18.
 */

public class Message {
    private String rawMessage;
    private MessageActionsEnum messageAction;
    private String fromBankAccountNumber;
    private String toBankAccountNumber;
    private double amount;
    private String reason;


    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public MessageActionsEnum getMessageAction() {
        return messageAction;
    }

    public void setMessageAction(MessageActionsEnum messageAction) {
        this.messageAction = messageAction;
    }

    public String getFromBankAccountNumber() {
        return fromBankAccountNumber;
    }

    public void setFromBankAccountNumber(String fromBankAccountNumber) {
        this.fromBankAccountNumber = fromBankAccountNumber;
    }

    public String getToBankAccountNumber() {
        return toBankAccountNumber;
    }

    public void setToBankAccountNumber(String toBankAccountNumber) {
        this.toBankAccountNumber = toBankAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
