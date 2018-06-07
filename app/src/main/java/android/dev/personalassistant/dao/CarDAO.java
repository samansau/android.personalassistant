package android.dev.personalassistant.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.Car;
import android.dev.personalassistant.entities.Card;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface CarDAO {
    @Insert
    public void insertCars(List<Car> cars);


    @Insert
    public void insertCar(Car car);

    @Update
    public void updateCars(Car car);

    @Query("select * from Car where carNumber = :carNumber")
    public Car fetchCarByCarNumber(String carNumber);

    @Query("select * from Car")
    public List<Car> fetchAllCars();


    @Delete
    public void deleteCar(Car car);


}
