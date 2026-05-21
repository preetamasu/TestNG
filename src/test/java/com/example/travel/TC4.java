package com.example.travel;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC4 {

    @Test
    void startCar(){
        System.out.println("Car started");
        Assert.fail();
    }

    @Test(dependsOnMethods = {"startCar"})
    void driveCar(){
        System.out.println("Car driving");
    }

    @Test(dependsOnMethods = {"driveCar"})
    void stopCar(){
        System.out.println("Car stopped");
    }

    @Test(dependsOnMethods = {"driveCar","stopCar"},alwaysRun = true)
    void parkCar(){
        System.out.println("Park it benito");
    }
}
