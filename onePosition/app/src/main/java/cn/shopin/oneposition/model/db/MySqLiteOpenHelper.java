package cn.shopin.oneposition.model.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.inject.Inject;

import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.di.component.DaggerActivityComponent;
import cn.shopin.oneposition.di.component.DaggerFragmentComponent;

/**
 * Created by zcs on 2017/3/22.
 */

public class MySqLiteOpenHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase sqlDbInstance;

    @Inject
    public MySqLiteOpenHelper() {
        this(Myapplication.getInstance(), "opdb", null, 1);
        Log.d("MySqLiteOpenHelper", "MySqLiteOpenHelper  初始化");
    }

    public MySqLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("sqlOP", "MySqLiteOpenHelper");
    }

    public MySqLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("sqlOP", "---onCreate---");
        String sql = "create table mv_collect(" +
                "sid integer primary key," +
                "id integer not null," +
                "title varchar(20)," +
                "summary varchar(50)," +
                "sharecount integer," +
                "commentcount integer," +
                "type varchar(10)," +
                "img_url varchar(200));";
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d("sqlOP", e.getMessage());
        }
    }

    public SQLiteDatabase getSqlDBInstance() {
        if (sqlDbInstance == null) {
            sqlDbInstance = getReadableDatabase();
        }
        return sqlDbInstance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 2017/3/22 在此方法中 alter table
        // db.execSQL("alter table 表名 add 字段 类型 not null default '默认值'");
        //亦可以新建表 db.execSQL("create table tab2(id integer primary key autoincrement , name text)");
        Log.d("sqlOP", "onUpgrade");
    }
}
