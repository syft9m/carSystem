package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(@RequestBody Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping(value = "insertCar", produces = "application/json")
    public JSONResult insertCar(@RequestBody Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 汽车销售
     * 通过carName,carSeries查看
     * @param  car
     * @return
     */
    @PostMapping(value = "buyCar", produces = "application/json")
    public JSONResult buyCar(@RequestBody Car car){
        String message = carService.buyCar(car.getCarName(), car.getCarSeries(), car.getCarQuantity());
        switch (message) {
            case "购买成功":{
                return JSONResult.ok(message);
            }
            default:
                return JSONResult.errorMsg(message);
        }
    }

    @GetMapping(value = "carPage")
    public JSONResult carPage(@RequestParam(name = "carname") String carName, @RequestParam int start, @RequestParam int end){
        List<Car> page = carService.findByCarName(carName, start, end);
        return JSONResult.ok(page);
    }
}
