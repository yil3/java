package com.yil3.pojo;

import java.util.HashMap;
import java.util.Map;

public class StaticCar {
    private static Map<Integer,Car> carMap;
    static {
        carMap = new HashMap<>();
        carMap.put(1,new Car(1001,"宝马"));
        carMap.put(2,new Car(1002,"奔驰"));
    }

    public static Car getCat(int id){
        return carMap.get(id);
    }
}
