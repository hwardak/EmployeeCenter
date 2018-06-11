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


    public DBDataAccess(Context context) {
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

    public boolean doesIdExist(String employeeId) {
        this.open();
        Cursor cursor =
                database.rawQuery("SELECT 1 FROM " + DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES
                        + " WHERE _id= \"" + Integer.valueOf(employeeId) + "\" LIMIT 1", null);


        Log.d(LOGTAG, "Returned " + cursor.getCount() + " rows");

        if (cursor.getCount() > 0) {
            this.close();
            return true;
        } else {
            this.close();
            return false;
        }

    }


    public boolean addEmployeeToTable(ArrayList<String> string) {
       /*
    Order in which values are stored in ArrayList:
        0   employeeId
        1   first_name
        3   middle_name
        4   last_name
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
        for (int i = 0; i < string.size(); i++) {
            values.put(ALL_EMPLOYEE_TABLE_COLUMNS[i], string.get(i));
        }
        database.insert(DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES, null, values);


        return true;
    }

    public ArrayList<String> getEmployeeList() {
        this.open();
        ArrayList<String> employeeList = new ArrayList<>();
        String employeeRow = "";

        Cursor cursor
                = database.rawQuery("Select * from "
                + DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES
                + ";", null);

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                employeeRow = "";

                employeeRow += i + 1 + ") ";
                employeeRow += cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_FIRST_NAME));
                employeeRow += " ";
                employeeRow += cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_LAST_NAME));

                employeeRow += "\t\t";
                employeeRow += cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_ID));


                employeeList.add(employeeRow);

            }

        }
        this.close();
        return employeeList;
    }


    public Cursor getEmployeeListCursor() {
        this.open();
        Cursor cursor
                = database.rawQuery("Select * from "
                + DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES
                + ";", null);

//        this.close();
        return cursor;
    }

    public ArrayList<String> getEmployeeDetails(int id) {

        this.open();
        ArrayList<String> employeeDetails = new ArrayList<>();

        Cursor cursor
                = database.rawQuery("SELECT * from "
                + DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES
                + " WHERE "
                + DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_ID
                + " = "
                + id
                + ";", null);

        cursor.moveToNext();

        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_FIRST_NAME)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_MIDDLE_NAME)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_LAST_NAME)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_DATE_OF_BIRTH)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_SIN)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_EMAIL)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_PHONE_NUMBER)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_STREET_ADDRESS)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_CITY)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_PROVINCE)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_POSTAL_CODE)));
        employeeDetails.add(cursor.getString(cursor.getColumnIndex(DBOpenHelperEmployeeCenter.EMPLOYEES_COLUMN_STARTING_DATE)));


        this.close();
        return employeeDetails;

    }


}

//    public ArrayList<ArrayList<String>> getAllEmployeeData(){
//
//    }
