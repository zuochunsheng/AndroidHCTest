package com.android.myapplicationtest.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Unique;

@Entity
public class UserEntity {
    @Id
    long id;

    @Unique
    @Index
    @NameInDb("USERNAME")
    public String username;

    public UserEntity(String username) {
        this.username = username;
    }
    //为了ObjectBox在构造实体时提高性能，还需要提供一个全属性构造函数
    public UserEntity(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
