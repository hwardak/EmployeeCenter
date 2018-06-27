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
    private static int DATABASE_VERSION = 2;

    public DBOpenHelperEmployeeCenter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * Table and column names.
     */
    static final String TABLE_EMPLOYEES = "employeesTable";
    static final String EMPLOYEES_COLUMN_ID = "_id";
    static final String EMPLOYEES_COLUMN_FIRST_NAME = "firstName";
    static final String EMPLOYEES_COLUMN_MIDDLE_NAME = "middleName";
    static final String EMPLOYEES_COLUMN_LAST_NAME = "lastName";
    static final String EMPLOYEES_COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    static final String EMPLOYEES_COLUMN_SIN = "sin";
    static final String EMPLOYEES_COLUMN_EMAIL = "email";
    static final String EMPLOYEES_COLUMN_PHONE_NUMBER = "phoneNumber";
    static final String EMPLOYEES_COLUMN_STREET_ADDRESS = "streetAddress";
    static final String EMPLOYEES_COLUMN_CITY = "city";
    static final String EMPLOYEES_COLUMN_PROVINCE = "province";
    static final String EMPLOYEES_COLUMN_POSTAL_CODE = "postalCode";
    static final String EMPLOYEES_COLUMN_STARTING_DATE = "startingDate";
    static final String EMPLOYEES_COLUMN_PICTURE_PATH = "profilePicturePath";


    private static final String CREATE_EMPLOYEES_TABLE
            = "CREATE TABLE "
            + TABLE_EMPLOYEES
            + " ("
            + EMPLOYEES_COLUMN_ID + " INTEGER PRIMARY KEY, "
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
            + EMPLOYEES_COLUMN_STARTING_DATE + " TEXT,"
            + EMPLOYEES_COLUMN_PICTURE_PATH + " TEXT "
            + ");";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEES_TABLE);
        Log.d(LOGTAG, "Employees table created. Version: " + DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE If EXISTS " + TABLE_EMPLOYEES);
        this.onCreate(db);

    }
}
