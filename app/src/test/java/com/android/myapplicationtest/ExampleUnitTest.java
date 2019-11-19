package com.android.myapplicationtest;

import com.android.myapplicationtest.util.RxTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        RxTest.print();

        assertEquals(4, 2 + 2);
    }
}