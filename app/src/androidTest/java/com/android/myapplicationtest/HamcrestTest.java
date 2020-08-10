//package com.android.myapplicationtest;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.JMock1Matchers.equalTo;
//import static org.hamcrest.Matchers.anyOf;
//import static org.hamcrest.Matchers.hasItem;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(JUnit4.class)
//public class HamcrestTest {
//    private List<String> hamcrestTestList;
//
//    @Before
//    public void setUp() {
//        hamcrestTestList = new ArrayList<>();
//        hamcrestTestList.add("first element");
//        hamcrestTestList.add("second element");
//        hamcrestTestList.add("third element");
//    }
//
//    @Test
//    public void assertWithJunitTest() {
//        assertTrue(hamcrestTestList.contains("first element")
//                || hamcrestTestList.contains("second element")
//                || hamcrestTestList.contains("third element"));
//    }
//
//
//    //同上
////    逻辑
////    allOf -检查是否包含所有的匹配器，相当于与(&&)；
////    anyOf -检查是否包含匹配器中的一个，相当于(||)；
////    not - 检查是否与匹配器相反，相当于非(!)；
//    @Test
//    public void assertWithHamcrestTest(){
//        assertThat(hamcrestTestList, hasItem(anyOf(equalTo("first element"), equalTo("second element"),
//                equalTo("third element"))));
//    }
//}
