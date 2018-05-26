package hwardak.employeecenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HWardak on 2018-05-22.
 */

public class DBDataAccess {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String LOGTAG = "dbAccess";

    public static final String[] ALL_EMPLOYEE_TABLE_COLUMNS = {
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_ID,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_FIRST_NAME,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_MIDDLE_NAME,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_LAST_NAME,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_DATE_OF_BIRTH,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_SIN,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_EMAIL,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_PHONE_NUMBER,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_STREET_ADDRESS,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_CITY,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_PROVINCE,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_POSTAL_CODE,
            DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_STARTING_DATE

    };


    public DBDataAccess(Context context){
        dbHelper = new DBOpenHelperEmployeeCenter(context);
    }


    /**
     * Opens Database.
     */
    public void open() {
        Log.d(LOGTAG, "Database opened.");
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Closes Database.
     */
    public void close() {
        Log.d(LOGTAG, "Database closed.");
        dbHelper.close();
    }

    public boolean doesIdExist(String employeeId){
        this.open();
        Cursor cursor =
                database.rawQuery("SELECT 1 FROM " + DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES
                        + " WHERE _id= \"" +  Integer.valueOf(employeeId) + "\" LIMIT 1", null);


        Log.d(LOGTAG, "Returned " + cursor.getCount() + " rows");

        if (cursor.getCount() > 0) {
            this.close();
            return true;
        } else {
            this.close();
            return false;
        }

    }


    public boolean addEmployeeToTable(ArrayList<String> string){
       /*
    Order in which values are stored in ArrayList:
        0   id
        1   first_name
        3   last_name
        4   middle_name
        5   date_of_birth
        6   sin_number
        7   email
        8   phone_number
        9   street_addres
        10  city
        11  province
        12  postal_code
        13  starting_date
     */
        this.open();
        ContentValues values = new ContentValues();
        for(int i = 0; i < string.size(); i++){
            values.put(ALL_EMPLOYEE_TABLE_COLUMNS[i], string.get(i));
        }
        database.insert(DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES, null, values);


        return true;
    }

}
