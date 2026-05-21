package com.example.travel;

import org.testng.annotations.*;

public class TC3 {

    @BeforeClass
    void beforeClass(){
        System.out.println("This is done before the class");
    }

    @AfterClass
    void afterClass(){
        System.out.println("This is done after the class");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("This is done before each method");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("This is the done after the test");
    }

    @Test
    void test3(){
        System.out.println("This is test1");
    }

    @Test
    void test4(){
        System.out.println("This is test2");
    }
}
