package android.dev.personalassistant.dao.kym;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteConstraintException;
import android.dev.personalassistant.entities.kym.BankAccount;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface BankAccountDAO {
    @Insert
    public void insertBankAccounts(List<BankAccount> bankAccounts);


    @Insert
    public void insertBankAccount(BankAccount bankAccount);

    @Update
    public void updateBankAccount(BankAccount bankAccount);

    @Query("select * from BankAccount where accountNumber = :accountNumber")
    public BankAccount fetchBankAccountByAccountNumber(String accountNumber);

    @Query("select * from BankAccount where bankAccountId = :bankAccountId")
    public BankAccount fetchBankAccountByBankAccountId(int bankAccountId);

    @Query("select * from BankAccount")
    public List<BankAccount> fetchAllBankAccounts();


    @Delete
    public void deleteBankAccount(BankAccount bankAccount) throws SQLiteConstraintException;


}
