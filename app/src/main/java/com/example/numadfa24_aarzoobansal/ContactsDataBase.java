package com.example.numadfa24_aarzoobansal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import com.example.numadfa24_aarzoobansal.model.ContactDetails;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<ContactDetails> fetchParticularData (int contactId){
        ArrayList<ContactDetails> userInfo = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String fetchQuery = "SELECT * from " + FinalDatabaseValues.TABLE_NAME +" WHERE " + FinalDatabaseValues.COLUMN_ID + " = " + contactId;

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(fetchQuery, null);
            while(cursor.moveToNext()){
                userInfo.add(new ContactDetails(contactId, cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            }
            cursor.close();
            db.close();
        }
        return userInfo;
    }

    public int updateRecord (String contactId, String userName, String userPhone, String userAlternatePhone, String userEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FinalDatabaseValues.COLUMN_NAME, userName);
        values.put(FinalDatabaseValues.COLUMN_Phone, userPhone);
        values.put(FinalDatabaseValues.COLUMN_EMAIL, userEmail);
        values.put(FinalDatabaseValues.COLUMN_ALTERNATE, userAlternatePhone);

        int rows = db.update(FinalDatabaseValues.TABLE_NAME, values, FinalDatabaseValues.COLUMN_ID + " = ?",
                new String[] { contactId});
        db.close();
        return rows;
    }
}
