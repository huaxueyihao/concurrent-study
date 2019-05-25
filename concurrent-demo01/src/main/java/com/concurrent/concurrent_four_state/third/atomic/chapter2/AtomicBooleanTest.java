package com.concurrent.concurrent_four_state.third.atomic.chapter2;

import org.junit.Test;


import java.util.concurrent.atomic.AtomicBoolean;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicBooleanTest {

    @Test
    public void testCreateWithoutArguments(){
        AtomicBoolean bool = new AtomicBoolean();
        assertFalse(bool.get());
    }

    @Test
    public void testCreateWithArguments(){
        AtomicBoolean bool = new AtomicBoolean(true);
        assertTrue(bool.get());
    }

    @Test
    public void testGetAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.getAndSet(false);
        assertTrue(result);
        assertTrue(bool.get());
    }





}
