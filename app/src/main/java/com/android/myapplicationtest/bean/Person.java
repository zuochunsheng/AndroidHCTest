package com.android.myapplicationtest.bean;

public class Person {
    private String name;
    private int age;
    private String number;
    private Birthday birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public Person() {
        super();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Person(String name, int age, Birthday birthday) {
        super();
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", birthday="
                + birthday + "]";
    }

}
