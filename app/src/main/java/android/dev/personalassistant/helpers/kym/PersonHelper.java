package android.dev.personalassistant.helpers.kym;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.dev.personalassistant.entities.kym.Person;
import android.dev.personalassistant.enums.ExpenseTagCategory;
import android.dev.personalassistant.enums.Relations;
import android.dev.personalassistant.vo.kym.PersonVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class PersonHelper {


    public void deletePerson(final PersonalAssistantDatabase personalAssistantDatabase, final String personName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person();
                try {
                    PersonVO personVOOld=fetchPersonVOByPersonName(personalAssistantDatabase, personName);
                    person.setPersonId(personVOOld.getPersonId());
                    personalAssistantDatabase.getPersonDAO().deletePerson(person);
                }catch (InterruptedException ie){
                    Log.e("deletePerson",ie.getStackTrace().toString());
                }

            }
        }).start();
    }


    public void persistPerson(final PersonalAssistantDatabase personalAssistantDatabase, final PersonVO personVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person();
                ExpenseTag tag=new ExpenseTag();

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
                List<Person> allPersons=personalAssistantDatabase.getPersonDAO().fetchAllPersons();
                if(!personVO.getRelation().equals(Relations.Self.toString()) || allPersons.stream().filter(p->p.getRelation().equals(Relations.Self.toString())).count()==0){
                    if (!personVO.isNew()) {

                        tag.setTagName(personVO.getExpenseTag());
                        tag.setTagId(personVO.getExpenseTagId());

                        personalAssistantDatabase.getExpenseTagDAO().updateExpenseTags(tag);

                        person.setPersonId(personVO.getPersonId());
                        personalAssistantDatabase.getPersonDAO().updatePersons(person);
                    } else {
                        tag.setTagName(personVO.getExpenseTag());
                        tag.setTagCategory(ExpenseTagCategory.ExpensedFor.getVal());
                        personalAssistantDatabase.getExpenseTagDAO().insertExpenseTag(tag);

                        ExpenseTag insertedTag=personalAssistantDatabase.getExpenseTagDAO().fetchExpenseTagByName(tag.getTagName());
                        person.setTagId(insertedTag.getTagId());
                        personalAssistantDatabase.getPersonDAO().insertPerson(person);
                    }
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
                    ExpenseTag tag=personalAssistantDatabase.getExpenseTagDAO().fetchExpenseTagById(person.getTagId());
                    personVO.setPersonId(person.getPersonId());
                    personVO.setFullName(person.getFullName());
                    personVO.setRelation(person.getRelation());
                    personVO.setDob(person.getDob());
                    personVO.setPanCardNumber(person.getPanCardNumber());
                    personVO.setAadharCardNumber(person.getAadharCardNumber());
                    personVO.setPassportNumber(person.getPassportNumber());
                    personVO.setPassportExpiry(person.getPassportExpiry());
                    personVO.setDrivingLisenceNumber(person.getDrivingLisenceNumber());
                    personVO.setDrivingLisenceExpiry(person.getDrivingLisenceExpiry());
                    personVO.setExpenseTagId(person.getTagId());
                    personVO.setExpenseTag(tag.getTagName());

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
                        personVO.setPersonId(person.getPersonId());
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
