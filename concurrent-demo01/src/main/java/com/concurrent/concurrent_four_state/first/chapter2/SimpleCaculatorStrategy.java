package com.concurrent.concurrent_four_state.first.chapter2;

/**
 * @Description: 策略者模式
 * @Author: maoruiquan
 * @Date:
 * @Version:
 **/
public class SimpleCaculatorStrategy implements CalculatorStrategy {


    private final static double SALARY_RATE= 0.1;
    private final static double BONUS_RATE= 0.15;

    @Override
    public double calculate(double salary, double bonus) {


        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
