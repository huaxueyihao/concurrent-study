package com.concurrent.concurrent_four_state.first.chapter2;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class TaxCalaculatorMain {

    public static void main(String[] args) {

//        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d,2000d){
//            @Override
//            protected double calcTax() {
//                return getSalary()*0.1+getBonus()*0.15;
//            }
//        };
//        double tax = taxCalaculator.calculate();
//        System.out.println(tax);

//        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d,2000d);
//        SimpleCaculatorStrategy simpleCaculatorStrategy = new SimpleCaculatorStrategy();
//        taxCalaculator.setCalculatorStrategy(simpleCaculatorStrategy);
//
//        System.out.println(taxCalaculator.calculate());

        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d, (s, d) -> {
            return s * 0.1 + d * 0.15;
        });

        System.out.println(taxCalaculator.calculate());


    }
}
