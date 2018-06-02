package android.dev.personalassistant.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Card;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface CardDAO {
    @Insert
    public void insertCards(List<Card> cards);


    @Insert
    public void insertCard(Card card);

    @Update
    public void updateCards(Card card);

    @Query("select * from Card where cardNumber = :cardNumber")
    public Card fetchCardByCardNumber(String cardNumber);

    @Query("select * from Card")
    public List<Card> fetchAllCards();


    @Delete
    public void deleteCard(Card card);


}
