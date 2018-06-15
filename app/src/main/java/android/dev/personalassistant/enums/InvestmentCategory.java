package android.dev.personalassistant.enums;

/**
 * Created by saurabh on 6/9/18.
 */

public enum InvestmentCategory {

    Savings("Savings"),
    FixedDeposit("Fixed Deposit"),
    PPF("Public Provident Fund"),
    EPF("Employee Provident Fund"),
    RecurringDeposit("Recurring Deposit"),
    MutualFund("Mutual Fund"),
    SIP("Systematic Investment Plan"),
    Insurance("Insurance");


    private String displayValue;


    InvestmentCategory(String displayValue){
        this.displayValue=displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }

    public static InvestmentCategory getInvestmentCategoryFromDisplayString(String displayString) {
        for (InvestmentCategory investmentCategory : InvestmentCategory.values()) {
            if (investmentCategory.displayValue.equalsIgnoreCase(displayString)) {
                return investmentCategory;
            }
        }
        return null;
    }
}
