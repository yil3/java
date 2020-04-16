package com.yil3.instance;

import com.yil3.pojo.Car;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CarInstance {
    private Map<Integer, Car> carMap;

    public CarInstance(){
        carMap = new HashMap<>();
        carMap.put(1,new Car(1001,"宝马"));
        carMap.put(2,new Car(1002,"奔驰"));
    }
    public Car getCar(int id){
        return carMap.get(id);
    }
}
