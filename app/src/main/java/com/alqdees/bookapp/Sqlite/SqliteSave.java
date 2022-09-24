package com.alqdees.bookapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.alqdees.bookapp.Model.ModelPdf;

public class SqliteSave extends SQLiteOpenHelper {

    public static final String DB_NAME = "Book.db";
    public static final String DB_TABLE = "BookTable";
    public static final String DB_ID = "ID";
    public static final String NAME = "name";
    public static final String Category = "category";
    public static final String Book = "Book";
    public static final int Version = 1;


    public SqliteSave(@Nullable Context context) {
        super(context, DB_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + DB_TABLE +
                "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT   UNIQUE, "
                + Category + " TEXT, " + Book + " BLOB " + ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
    }

    private long AddBooks(ModelPdf model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,model.getTitle());
        cv.put(Category,model.getCategoryId());
//        cv.put(TYPE,model.getType());
//        cv.put(LOCATION,model.getLocation());
        long result = db.insert(DB_TABLE,null,cv);
        db.close();
        return result;

    }
}
