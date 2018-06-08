package android.dev.personalassistant.vo;

/**
 * Created by saurabh on 6/2/18.
 */

public class CarVO {

    private boolean isNew;
    private String carNumber;
    private String carName;
    private String carInsuranceNumber;
    private String carInsuranceExpiry;
    private String carPUCExpiry;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
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
        return "CarVO{" +
                "isNew=" + isNew +
                ", carNumber='" + carNumber + '\'' +
                ", carName='" + carName + '\'' +
                ", carInsuranceNumber='" + carInsuranceNumber + '\'' +
                ", carInsuranceExpiry='" + carInsuranceExpiry + '\'' +
                ", carPUCExpiry='" + carPUCExpiry + '\'' +
                '}';
    }
}
