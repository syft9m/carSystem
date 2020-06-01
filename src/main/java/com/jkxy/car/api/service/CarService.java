package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.Car;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    Car findByNameAndSeries(String carName, String carSeries);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);

    String buyCar(String carName, String carSeries, int num);

    List<Car> findByCarName(String carName, int start, int end);
}
