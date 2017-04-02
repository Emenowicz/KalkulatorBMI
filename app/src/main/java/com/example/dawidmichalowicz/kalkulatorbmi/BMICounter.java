package com.example.dawidmichalowicz.kalkulatorbmi;

/**
 * Created by Dawid Michałowicz on 24.03.2017.
 */

public class BMICounter {
    static final int maxCm = 250;
    static final int minCm = 50;
    static final int maxKg = 300;
    static final int minKg = 10;

    public float countBMI(float weight, float height) {
        if (isValidWeight(weight)&&isValidHeight(height)){
            return (weight/(float)Math.pow(height/100f,2));
        }
        else throw new IllegalArgumentException("Złe argumenty!");
    }

    public boolean isValidWeight(float weight){
        return minKg<weight&&weight<=maxKg;
    }


    public boolean isValidHeight(float height){
        return minCm<height&&height<=maxCm;
    }

}
