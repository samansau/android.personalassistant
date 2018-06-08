package android.dev.personalassistant.helpers;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Car;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.vo.CarVO;
import android.dev.personalassistant.vo.CardVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class CarHelper {


    public void persistCar(final PersonalAssistantDatabase personalAssistantDatabase, final CarVO carVO,final String oldCarNumber){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Car car = new Car();

                car.setCarNumber(carVO.getCarNumber());
                car.setCarName(carVO.getCarName());
                car.setCarInsuranceNumber(carVO.getCarInsuranceNumber());
                car.setCarInsuranceExpiry(carVO.getCarInsuranceExpiry());
                car.setCarPUCExpiry(carVO.getCarPUCExpiry());
                try {
                    CarVO carVOOld = fetchCarVOByCarNumber(personalAssistantDatabase, oldCarNumber);



                    if (!carVO.isNew()) {
                        car.setCarId(carVOOld.getCarId());
                        personalAssistantDatabase.getCarDAO().updateCars(car);
                    } else {
                        personalAssistantDatabase.getCarDAO().insertCar(car);
                    }
                    Log.d("Car Object : ", car.toString());
                }
                catch (InterruptedException ie){
                    Log.e("InterruptedException " ,ie.getStackTrace().toString());
                }


            }
        }).start();
    }


    public CarVO fetchCarVOByCarNumber(final PersonalAssistantDatabase personalAssistantDatabase,final String carNumber) throws InterruptedException{
        final CarVO carVO=new CarVO();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Car car = personalAssistantDatabase.getCarDAO().fetchCarByCarNumber(carNumber);
                if(car!=null){
                    carVO.setCarId(car.getCarId());
                    carVO.setCarNumber(carNumber);
                    carVO.setCarName(car.getCarName());
                    carVO.setCarInsuranceNumber(car.getCarInsuranceNumber());
                    carVO.setCarInsuranceExpiry(car.getCarInsuranceExpiry());
                    carVO.setCarPUCExpiry(car.getCarPUCExpiry());
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return carVO;
    }


    public List<CarVO> fetchAllCarVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<CarVO> carVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<Car> cars = personalAssistantDatabase.getCarDAO().fetchAllCars();
                if(cars!=null){
                    CarVO carVOBlank=new CarVO();
                    carVOBlank.setCarNumber("");
                    carVOs.add(carVOBlank);
                    for(Car car:cars){
                        CarVO carVO=new CarVO();
                        carVO.setCarId(car.getCarId());
                        carVO.setCarNumber(car.getCarNumber());
                        carVO.setCarName(car.getCarName());
                        carVO.setCarInsuranceNumber(car.getCarInsuranceNumber());
                        carVO.setCarInsuranceExpiry(car.getCarInsuranceExpiry());
                        carVO.setCarPUCExpiry(car.getCarPUCExpiry());
                        carVOs.add(carVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return carVOs;
    }
}
