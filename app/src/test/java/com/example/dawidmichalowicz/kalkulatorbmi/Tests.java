package com.example.dawidmichalowicz.kalkulatorbmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dawid Michałowicz on 27.03.2017.
 */

public class Tests {
    @Test
    public void height_under_zero_is_incorrect() throws Exception {
        //GIVEN
        float testHeight = -1f;
        //WHERE
        BMICounter counter = new BMICounter();
        //THEN
        assertFalse(counter.isValidHeight(testHeight));
    }

    @Test
    public void height_over_250_is_incorrect() throws Exception {
        //GIVEN
        float testHeight = 251f;
        //WHERE
        BMICounter counter = new BMICounter();
        //THEN
        assertFalse(counter.isValidHeight(testHeight));
    }

    @Test
    public void weight_under_zero_is_incorrect() throws Exception {
        //GIVEN
        float testWeight = -1f;
        //WHERE
        BMICounter counter = new BMICounter();
        //THEN
        assertFalse(counter.isValidWeight(testWeight));
    }

    @Test
    public void weight_over_300_is_incorrect() throws Exception {
        //GIVEN
        float testWeight = 301f;
        //WHERE
        BMICounter counter = new BMICounter();
        //THEN
        assertFalse(counter.isValidWeight(testWeight));
    }
}
