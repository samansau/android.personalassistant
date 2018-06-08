package android.dev.personalassistant.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by saurabh on 6/7/18.
 */

@Entity
public class Car {

    @PrimaryKey(autoGenerate = true)
    private int carId;
    @NonNull
    private String carNumber;
    private String carName;
    private String carInsuranceNumber;
    private String carInsuranceExpiry;
    private String carPUCExpiry;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @NonNull
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(@NonNull String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarInsuranceNumber() {
        return carInsuranceNumber;
    }

    public void setCarInsuranceNumber(String carInsuranceNumber) {
        this.carInsuranceNumber = carInsuranceNumber;
    }

    public String getCarInsuranceExpiry() {
        return carInsuranceExpiry;
    }

    public void setCarInsuranceExpiry(String carInsuranceExpiry) {
        this.carInsuranceExpiry = carInsuranceExpiry;
    }

    public String getCarPUCExpiry() {
        return carPUCExpiry;
    }

    public void setCarPUCExpiry(String carPUCExpiry) {
        this.carPUCExpiry = carPUCExpiry;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", carName='" + carName + '\'' +
                ", carInsuranceNumber='" + carInsuranceNumber + '\'' +
                ", carInsuranceExpiry='" + carInsuranceExpiry + '\'' +
                ", carPUCExpiry='" + carPUCExpiry + '\'' +
                '}';
    }
}
