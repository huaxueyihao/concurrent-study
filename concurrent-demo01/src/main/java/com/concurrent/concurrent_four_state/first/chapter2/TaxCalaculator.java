package com.concurrent.concurrent_four_state.first.chapter2;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class TaxCalaculator {

    private final double salary;

    private final double bonus;

    private final CalculatorStrategy calculatorStrategy;

    public TaxCalaculator(double salary, double bonus,CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }
}
