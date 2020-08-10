package com.android.myapplicationtest.util;

import android.util.Log;

import com.android.myapplicationtest.bean.Birthday;
import com.android.myapplicationtest.bean.Person;


public class test {

    private static String TAG = "jackson";
    public static void main(String[] args) {

        Birthday birthday = new Birthday(2000, 4, 28);
        Person person = new Person("zhang san", 29, birthday);
        try {
            System.out.println("person ->string: " + JSONUtil.getJsonString(person));
        } catch (Exception e) {
            System.out.println( "onCreate: " + e.toString());
            e.printStackTrace();
        }

        String jsonStr = "{\"name\":\"zhang san\",\"birthday\":{\"day\":28,\"month\":4,\"year\":2000},\"age\":29}";
        try {
            System.out.println("string ->object: " + JSONUtil.toObject(jsonStr, Person.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
