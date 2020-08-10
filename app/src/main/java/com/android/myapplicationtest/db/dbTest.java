package com.android.myapplicationtest.db;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class dbTest {
    private static String TAG = "dbTest";

    public static void dbtest() {

        //第一种创建表的方式的操作
        User user = new User();
        user.id = UUID.randomUUID();
        user.name = "kaka";
        user.age = 18;
        ModelAdapter<User> adapter = FlowManager.getModelAdapter(User.class);
        //插入User
        adapter.insert(user);

        //修改名字
        user.name = "Test";
        adapter.update(user);

        User userQuery = SQLite.select().from(User.class).querySingle();
        Log.e(TAG, "id=" + userQuery.id + ",name=" + userQuery.name + ",age=" + userQuery.age);
    }

    public static void dbtest2() {
        //第二种创建表的方式

        User2Model userModel = new User2Model();
        userModel.setName("UserModel");
        userModel.setAge(new Random().nextInt(100));
        userModel.save();


        userModel.update();
        userModel.delete();


        List<User2Model> user2Models = SQLite.select().from(User2Model.class)
                 .where(User2Model_Table.age.greaterThan(18), User2Model_Table.name.eq("UserModel"))
                .orderBy(OrderBy.fromNameAlias(NameAlias.of("id")))
                .groupBy(NameAlias.of("id"))
                .queryList();
        if (user2Models.size() != 0) {
            for (User2Model user2Model : user2Models) {
                Log.e(TAG, "id=" + user2Model.getId() + ",name=" + user2Model.getName() + ",age=" + user2Model.getAge());
            }
        } else {
           // Toast.makeText(this, "数据为0", Toast.LENGTH_SHORT).show();
        }



        //异步事务
        FlowManager.getDatabase(AppDataBase.class).beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                for (int i=0;i<100;i++){
                    User2Model userModel=new User2Model();
                    userModel.setName("UserModel");
                    userModel.setAge(new Random().nextInt(100));
                    userModel.save(databaseWrapper);
                }
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                Log.e(TAG,"onSuccess()");
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.e(TAG,"onError()");
            }
        }).build().execute();
    }
}

