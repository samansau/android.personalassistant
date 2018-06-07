package android.dev.personalassistant.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by saurabh on 6/7/18.
 */

@Entity
public class Car {

    @PrimaryKey
    @NonNull
    private String carNumber;
    private String carName;
    private String carInsuranceNumber;
    private String carInsuranceExpiry;
    private String carPUCExpiry;


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


}
