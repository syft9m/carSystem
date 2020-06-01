package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public Car findByNameAndSeries(String carName, String carSeries) {
        return carDao.findByNameAndSeries(carName, carSeries);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String buyCar(String carName, String carSeries, int num) {
        Car buyCar = carDao.findByNameAndSeries(carName, carSeries);
        if (buyCar != null) {
            synchronized (buyCar) {
                buyCar = carDao.findByNameAndSeries(carName, carSeries);
                int quantity = buyCar.getCarQuantity() - num;
                if (quantity >= 0) {
                    buyCar.setCarQuantity(quantity);
                    carDao.updateById(buyCar);
                    return "购买成功";
                } else {
                    return "库存不足";
                }
            }
        } else {
            return "无该车型";
        }


    }

    @Override
    public List<Car> findByCarName(String carName, int start, int end) {
        if (start <= end) {
            int size = end - start + 1;
            List<Car> carPage = carDao.findByCarNamePage(carName, start - 1, size);
            return carPage;
        }
        return null;
    }
}
