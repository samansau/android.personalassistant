package android.dev.personalassistant.enums;

/**
 * Created by saurabh on 6/2/18.
 */

public enum Relations {


    Self("Myself"),
    Father("Father"),
    Mother("Mother"),
    Brother("Brother"),
    Sister("Sister"),
    Wife("Wife"),
    Son("Son"),
    Daughter("Daughter"),
    Friend("Friend");


    private String value;

    Relations(String value){
        this.value=value;
    };



    @Override
    public String toString() {
        return value;
    }

    public static Relations fromString(String text) {
        for (Relations relation : Relations.values()) {
            if (relation.value.equalsIgnoreCase(text)) {
                return relation;
            }
        }
        return null;
    }


}
