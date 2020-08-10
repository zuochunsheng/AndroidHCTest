package com.android.myapplicationtest.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.UUID;

//建表方式 1
@Table(database = AppDataBase.class)
public class User {
    @PrimaryKey
    UUID id;

    @Column
    String name;

    @Column
    int age;
}
