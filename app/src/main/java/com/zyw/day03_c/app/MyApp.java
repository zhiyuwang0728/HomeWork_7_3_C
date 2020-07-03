package com.zyw.day03_c.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.zyw.greendaodemo.db.DaoMaster;
import com.zyw.greendaodemo.db.DaoSession;

public class MyApp extends Application {

    private static MyApp myApp;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        initDb();
    }

    private void initDb() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db", null);

        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        writableDatabase.disableWriteAheadLogging();

        daoSession = new DaoMaster(writableDatabase).newSession();
    }

    public static MyApp getInstance() {
        return myApp;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
