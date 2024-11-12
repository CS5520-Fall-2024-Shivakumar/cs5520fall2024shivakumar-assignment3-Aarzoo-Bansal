package com.example.numadfa24_aarzoobansal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ContactsDataBase extends SQLiteOpenHelper {

    private Context context;


    public ContactsDataBase(@Nullable Context context){
        super(context, FinalDatabaseValues.DATABASE_NAME, null, FinalDatabaseValues.DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create an sql query
        String createTableQuery = "Create TABLE " + FinalDatabaseValues.TABLE_NAME +
                " ("+  FinalDatabaseValues.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FinalDatabaseValues.COLUMN_NAME + " TEXT, " +
                FinalDatabaseValues.COLUMN_Phone + " TEXT, " +
                FinalDatabaseValues.COLUMN_ALTERNATE +" TEXT, " +
                FinalDatabaseValues.COLUMN_EMAIL + " TEXT);";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+FinalDatabaseValues.TABLE_NAME);
        onCreate(db);
    }

    public long addContacts(String contactName, String contactPhone, String alternateNumber, String email){
        SQLiteDatabase db = this.getWritableDatabase(); //getting the object to write in the table
        ContentValues cv = new ContentValues();

        cv.put(FinalDatabaseValues.COLUMN_NAME, contactName);
        cv.put(FinalDatabaseValues.COLUMN_Phone, contactPhone);
        cv.put(FinalDatabaseValues.COLUMN_ALTERNATE, alternateNumber);
        cv.put(FinalDatabaseValues.COLUMN_EMAIL, email);

       long rowInsert =  db.insert(FinalDatabaseValues.TABLE_NAME, null, cv); //This command is used to insert into the database table
        db.close();
        return rowInsert;
    }

    Cursor readAllData() {
        String readQuery = "SELECT * FROM " + FinalDatabaseValues.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null; //cursor contains all the data from the database table
        if(db != null){
            cursor = db.rawQuery(readQuery, null);
        }
        return cursor;
    }

   public int deleteRecord(String contactId){
       SQLiteDatabase db = this.getWritableDatabase();

       String whereClause =  FinalDatabaseValues.COLUMN_ID + " = ?";
       String[] whereArgs = new String[] { contactId };
       int deleteRows = db.delete(FinalDatabaseValues.TABLE_NAME, whereClause, whereArgs);
       db.close();
       return deleteRows;
    }
}
