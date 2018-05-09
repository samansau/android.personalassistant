package android.dev.personalassistant.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by saurabh on 5/8/18.
 */

public class Owner {

    public Person person;
    public Date birthDay;
    public String panCardNumber;
    public String aadharCardNumber;
    public String passportNumber;
    public Date passportExpiry;
    List<Person> familyMembers;
    List<Residence> residences;

}
