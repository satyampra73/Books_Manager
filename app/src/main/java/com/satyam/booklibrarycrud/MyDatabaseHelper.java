package com.satyam.booklibrarycrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DatabaseName = "BookLibrary.db";
    private static final int DatabaseVersion = 1;
    private static final String Table_name = "my_library";
    private static final String Column_id = "_id";
    private static final String Column_Title = "book_id";
    private static final String Column_author = "book_author";
    private static final String Column_pages = "book_pages";


    public MyDatabaseHelper(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Table_name + " (" + Column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Title + " TEXT, "
                + Column_author + " TEXT, " + Column_pages + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);

    }

    void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_Title, title);
        cv.put(Column_author, author);
        cv.put(Column_pages, pages);
        long res = db.insert(Table_name, null, cv);
        if (res == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
        }

    }
}
