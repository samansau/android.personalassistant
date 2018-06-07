package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.entities.Person;
import android.dev.personalassistant.vo.CardVO;
import android.dev.personalassistant.vo.PersonVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.dev.personalassistant.utils.Keys.cardNumber;

/**
 * Created by saurabh on 6/2/18.
 */

public class PersonHelper {


    public void persistPerson(final PersonalAssistantDatabase personalAssistantDatabase, final PersonVO personVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person();

                person.setFullName(personVO.getFullName());
                person.setRelation(personVO.getRelation());
                person.setDob(personVO.getDob());
                person.setAadharCardNumber(personVO.getAadharCardNumber());
                person.setPanCardNumber(personVO.getPanCardNumber());
                person.setPassportNumber(personVO.getPassportNumber());
                person.setPassportExpiry(personVO.getPassportExpiry());
                person.setDrivingLisenceNumber(personVO.getDrivingLisenceNumber());
                person.setDrivingLisenceExpiry(personVO.getDrivingLisenceExpiry());



                Log.d("person Object : ",person.toString());
                if (!personVO.isNew()) {
                    personalAssistantDatabase.getPersonDAO().updatePersons(person);
                }else{
                    personalAssistantDatabase.getPersonDAO().insertPerson(person);
                }

            }
        }).start();
    }


    public PersonVO fetchPersonVOByPersonName(final PersonalAssistantDatabase personalAssistantDatabase,final String personName) throws InterruptedException{
        final PersonVO personVO=new PersonVO();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = personalAssistantDatabase.getPersonDAO().fetchPersonByPersonName(personName);
                if(person!=null){
                    personVO.setFullName(person.getFullName());
                    personVO.setRelation(person.getRelation());
                    personVO.setDob(person.getDob());
                    personVO.setPanCardNumber(person.getPanCardNumber());
                    personVO.setAadharCardNumber(person.getAadharCardNumber());
                    personVO.setPassportNumber(person.getPassportNumber());
                    personVO.setPassportExpiry(person.getPassportExpiry());
                    personVO.setDrivingLisenceNumber(person.getDrivingLisenceNumber());
                    personVO.setDrivingLisenceExpiry(person.getDrivingLisenceExpiry());
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return personVO;
    }


    public List<PersonVO> fetchAllPersonVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<PersonVO> personVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<Person> persons = personalAssistantDatabase.getPersonDAO().fetchAllPersons();
                if(persons!=null){
                    for(Person person:persons){
                        PersonVO personVO=new PersonVO();
                        personVO.setFullName(person.getFullName());
                        personVO.setRelation(person.getRelation());
                        personVO.setDob(person.getDob());
                        personVO.setPanCardNumber(person.getPanCardNumber());
                        personVO.setAadharCardNumber(person.getAadharCardNumber());
                        personVO.setPassportNumber(person.getPassportNumber());
                        personVO.setPassportExpiry(person.getPassportExpiry());
                        personVO.setDrivingLisenceNumber(person.getDrivingLisenceNumber());
                        personVO.setDrivingLisenceExpiry(person.getDrivingLisenceExpiry());
                        personVOs.add(personVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return personVOs;
    }
}
