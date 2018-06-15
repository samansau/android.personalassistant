package android.dev.personalassistant.enums;

/**
 * Created by saurabh on 6/10/18.
 */

public enum FDStatus {

    Active("Active"),
    Renewed("Renewed"),
    Liquidated("Liquidated");

    private String value;
    FDStatus(String value){
        this.value=value;
    }
    public String getValue(){
        return this.value;
    }
}
