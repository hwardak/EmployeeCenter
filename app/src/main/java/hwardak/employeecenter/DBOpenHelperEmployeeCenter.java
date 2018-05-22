package hwardak.employeecenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by HWardak on 2018-05-21.
 */

public class DBOpenHelperEmployeeCenter extends SQLiteOpenHelper {

    private static final String LOGTAG = "DATABASE: ";
    private static final String DATABASE_NAME ="employeeCenter.db";
    private static int DATABASE_VERSION = 1;

    public DBOpenHelperEmployeeCenter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * Table and column names.
     */
    private static final String TABLE_EMPLOYEES = "employeesTable";
    private static final String EMPLOYEES_COLUMN_ID = "_id";
    private static final String EMPLOYEES_COLUMN_FIRST_NAME = "firstName";
    private static final String EMPLOYEES_COLUMN_MIDDLE_NAME = "middleName";
    private static final String EMPLOYEES_COLUMN_LAST_NAME = "lastName";
    private static final String EMPLOYEES_COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    private static final String EMPLOYEES_COLUMN_SIN = "sin";
    private static final String EMPLOYEES_COLUMN_EMAIL = "email";
    private static final String EMPLOYEES_COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String EMPLOYEES_COLUMN_STREET_ADDRESS = "streetAddress";
    private static final String EMPLOYEES_COLUMN_CITY = "city";
    private static final String EMPLOYEES_COLUMN_PROVINCE = "province";
    private static final String EMPLOYEES_COLUMN_POSTAL_CODE = "postalCode";
    private static final String EMPLOYEES_COLUMN_STARTING_DATE = "startingDate";


    private static final String CREATE_EMPLOYEES_TABLE
            = "CREATE TABLE "
            + TABLE_EMPLOYEES
            + " ("
            +  EMPLOYEES_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + EMPLOYEES_COLUMN_FIRST_NAME + " TEXT, "
            + EMPLOYEES_COLUMN_MIDDLE_NAME + " TEXT, "
            + EMPLOYEES_COLUMN_LAST_NAME + " TEXT, "
            + EMPLOYEES_COLUMN_DATE_OF_BIRTH + " TEXT, "
            + EMPLOYEES_COLUMN_SIN + " TEXT, "
            + EMPLOYEES_COLUMN_EMAIL + " TEXT, "
            + EMPLOYEES_COLUMN_PHONE_NUMBER + " TEXT, "
            + EMPLOYEES_COLUMN_STREET_ADDRESS + " TEXT, "
            + EMPLOYEES_COLUMN_CITY + " TEXT, "
            + EMPLOYEES_COLUMN_PROVINCE + " TEXT, "
            + EMPLOYEES_COLUMN_POSTAL_CODE + " TEXT, "
            + EMPLOYEES_COLUMN_STARTING_DATE + " TEXT"
            + ");";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEES_TABLE);
        Log.d(LOGTAG, "Employees table created. Version: " + DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IS EXISTS " + TABLE_EMPLOYEES);

        this.onCreate(db);

    }
}
