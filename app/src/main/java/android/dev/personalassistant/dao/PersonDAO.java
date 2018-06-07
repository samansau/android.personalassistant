package android.dev.personalassistant.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.entities.Person;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface PersonDAO {
    @Insert
    public void insertPersons(List<Person> persons);


    @Insert
    public void insertPerson(Person person);

    @Update
    public void updatePersons(Person person);

    @Query("select * from Person where fullName = :personName")
    public Person fetchPersonByPersonName(String personName);

    @Query("select * from Person")
    public List<Person> fetchAllPersons();


    @Delete
    public void deletePerson(Person person);


}
