package hwardak.employeecenter;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by HWardak on 2018-06-27.
 */

public class ContentProvider extends android.content.ContentProvider {

    // The Java namespace for the Content Provider
    static final String PROVIDER_NAME = "hwardak.employeecenter.ContentProvider";

    // Assigned to a content provider so any application can access it
    // cpcontacts is the virtual directory in the provider
    static final String URL = "content://" + PROVIDER_NAME + "/cpemployees";
    static final Uri CONTENT_URL = Uri.parse(URL);

    static final String id = "id";
    static final String name = "name";
    static final int uriCode = 1;

    private static HashMap<String, String> values;


    // Used to match uris with Content Providers
    static final UriMatcher uriMatcher;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cpemployees", uriCode);
    }


    private SQLiteDatabase sqlDB;

    @Override
    public boolean onCreate() {
        DBOpenHelperEmployeeCenter dbOpenHelperEmployeeCenter = new DBOpenHelperEmployeeCenter(getContext());
        //TODO: perhaps change this to getReadableDatabase(), since we only need to read.
        sqlDB = dbOpenHelperEmployeeCenter.getWritableDatabase();
        if(sqlDB != null){
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        // Used to create a SQL query
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Set table to query
        queryBuilder.setTables(DBOpenHelperEmployeeCenter.TABLE_EMPLOYEES);

        // Used to match uris with Content Providers
        switch (uriMatcher.match(uri)) {
            case uriCode:

                // A projection map maps from passed column names to database column names
                queryBuilder.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Cursor provides read and write access to the database
        Cursor cursor = queryBuilder.query(sqlDB, projection, selection, selectionArgs, null,
                null, sortOrder);

        // Register to watch for URI changes
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // Used to match uris with Content Providers
        switch (uriMatcher.match(uri)) {

            // vnd.android.cursor.dir/employees states that we expect multiple pieces of data
            case uriCode:
                return "vnd.android.cursor.dir/cpemployees";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
