package com.android.myapplicationtest.bean;

public class Student {

    private final String name;
    private int grade = 1;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getGrade() {
        return grade;
    }

    private void goToSchool() {
        System.out.println(name + " go to school!");
    }
}
