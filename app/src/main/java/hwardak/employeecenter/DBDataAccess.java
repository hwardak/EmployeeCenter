package hwardak.employeecenter;

import android.content.Context;
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

    public boolean addEmployeeToTable(ArrayList<String> string){


        return true;
    }

}
