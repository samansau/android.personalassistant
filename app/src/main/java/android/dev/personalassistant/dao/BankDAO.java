package android.dev.personalassistant.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.Bank;
import android.dev.personalassistant.entities.Card;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface BankDAO {


    @Query("select * from Bank where bankName = :bankName and branch = :branch")
    public Bank fetchBankByBankNameAndBranch(String bankName,String branch);

    @Query("select * from Bank")
    public List<Bank> fetchAllBanks();



}
