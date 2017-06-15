package cn.shopin.oneposition.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.entity.movie.SubTypeEntity;

/**
 * Created by zcs on 2017/3/22.
 */

public class DBManager {
    private MySqLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DBManager dbManager;

    @Inject
    public DBManager(MySqLiteOpenHelper sqLiteOpenHelper) {
        Log.d("DBManager", "DBManager  初始化");
        this.sqLiteOpenHelper = sqLiteOpenHelper;
        sqLiteDatabase = this.sqLiteOpenHelper.getSqlDBInstance();
//        sqLiteOpenHelper = new MySqLiteOpenHelper(mContext);
//        sqLiteDatabase = sqLiteOpenHelper.getSqlDBInstance();
    }
//
//    public DBManager getDBMInstance(Context mContext) {
//        dbManager = new DBManager(mContext);
//        return dbManager;
//    }

    public long insert(NostalgicEntity entity) {
        ContentValues cValues = new ContentValues();
        cValues.put("id", entity.getId());
        cValues.put("title", entity.getTitle());
        cValues.put("summary", entity.getSummary());
        cValues.put("sharecount", entity.getSharecount());
        cValues.put("commentcount", entity.getCommentcount());
        cValues.put("type", entity.getSubtype().getName());
        cValues.put("img_url", entity.getPic());
        return sqLiteDatabase.insert("mv_collect", null, cValues);
    }

    public int delete(NostalgicEntity entity) {
        return sqLiteDatabase.delete("mv_collect", "id=?", new String[]{String.valueOf(entity.getId())});
    }

    public int delete(int id) {
        return sqLiteDatabase.delete("mv_collect", "id=?", new String[]{String.valueOf(id)});
    }

    public List<CollectEntity> query() {
        Cursor mCursor = sqLiteDatabase.rawQuery("select * from mv_collect", new String[]{});
        List<CollectEntity> collects = new ArrayList<>();
        while (mCursor.moveToNext()) {
            CollectEntity entity = new CollectEntity();
            entity.setId(mCursor.getInt(mCursor.getColumnIndex("id")));
            entity.setTitle(mCursor.getString(mCursor.getColumnIndex("title")));
            entity.setSummary(mCursor.getString(mCursor.getColumnIndex("summary")));
            entity.setSharecount(mCursor.getInt(mCursor.getColumnIndex("sharecount")));
            entity.setCommentcount(mCursor.getInt(mCursor.getColumnIndex("commentcount")));
            SubTypeEntity subTypeEntity = new SubTypeEntity();
            subTypeEntity.setName(mCursor.getString(mCursor.getColumnIndex("type")));
            entity.setSubtype(subTypeEntity);
            entity.setPic(mCursor.getString(mCursor.getColumnIndex("img_url")));
            collects.add(entity);
        }
        return collects;
    }

    public boolean query(int id) {
        Cursor mCursor = sqLiteDatabase.query("mv_collect", new String[]{"id"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (mCursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        sqLiteOpenHelper.onUpgrade(db, oldVersion, newVersion);
    }

    public void close() {
        sqLiteDatabase.close();
    }
}
