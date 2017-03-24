package cn.shopin.oneposition.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.entity.movie.MoviePieceEntity;
import cn.shopin.oneposition.entity.movie.SubTypeEntity;

/**
 * Created by zcs on 2017/3/22.
 */

public class DBManager {
    private MySqLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DBManager dbManager;

    public DBManager(Context mContext) {
        sqLiteOpenHelper = new MySqLiteOpenHelper(mContext);
        sqLiteDatabase = sqLiteOpenHelper.getSqlDBInstance();
    }

    public DBManager getDBMInstance(Context mContext) {
        dbManager = new DBManager(mContext);
        return dbManager;
    }

    public void insert(MoviePieceEntity entity) {
        Log.d("sqlOP", "insert : ---");
        ContentValues cValues = new ContentValues();
        cValues.put("id", entity.getId());
        cValues.put("title", entity.getTitle());
        cValues.put("summary", entity.getSummary());
        cValues.put("sharecount", entity.getSharecount());
        cValues.put("commentcount", entity.getCommentcount());
        cValues.put("type", entity.getSubtype().getName());
        cValues.put("img_url", entity.getPic());
        long id = sqLiteDatabase.insert("mv_collect", null, cValues);
        Log.d("sqlOP", "insert : " + String.valueOf(id));
    }

    public void delete(MoviePieceEntity entity) {
        Log.d("sqlOP", "delete : ---");
        int id = sqLiteDatabase.delete("mv_collect", "id=?", new String[]{String.valueOf(entity.getId())});
        Log.d("sqlOP", "delete : " + String.valueOf(id));
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
        Log.d("sqlOP", "query : " + String.valueOf(collects.size()));
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
