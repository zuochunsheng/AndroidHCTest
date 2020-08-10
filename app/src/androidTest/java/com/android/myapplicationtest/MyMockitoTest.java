package com.android.myapplicationtest;

import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.*;

public class MyMockitoTest {
    @Test
    public void testMockitoTest() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        //使用模拟对象(而不是真实创建出来的对象那)
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        // the following prints "first"
        System.out.println(mockedList.get(0));
        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }
}
