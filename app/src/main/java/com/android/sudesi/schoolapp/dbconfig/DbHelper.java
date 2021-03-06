package com.android.sudesi.schoolapp.dbconfig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.android.sudesi.schoolapp.R;


/**
 * Created by Admin on 27-10-2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SchoolApp.sqlite";

    public static final String TABLE_DB_SCHOOL ="StudentDetails";
    public static final String TABLE_DB_PARENT = "ParentDetails";
    public static final String TABLE_DB_TEACHER = "TeacherDetails";
    public static final String TABLE_DB_ATTENDANCE="Attendance_details";
    public static final String  TABLE_DB_TIMETABLE="TimeTableDetails";
    public static final String TABLE_DB_MARK_DAILYATTENDANCE="MarkDailyAttendance";

    public static final String TABLE_DIRECT_LEAD_CATEGORY_DTLS = "tabel_direct_lead_category_dtls";
    public static final String TABLE_DIRECT_LEAD = "table_Direct_lead";
    public static final String TABLE_M_CATEGORY = "table_M_Category";
    public static final String TABLE_DIRECT_LEAD_ST_DTLS = "table_direct_lead_st_dtls";
    public static final String TABLE_M_PINCODE_TABLE = "pincode";
    public static final String TABLE_CLIENT_DETAILS = "table_get_All_Client";
    public static final String TABLE_NOTIFICATION = "table_Notification";
    public static final String TABLE_ACCESS_HISTORY = "access_history";
    public static final String TABLE_EMPLOYEE_DETAILS="table_employee_details";
    public static final String TABLE_LOGOUT_DETAILS="table_Logout_Details";
    public static final String TABLE_STUDENT_DETAILS="CREATE TABLE `StudentDetails` (`Roll_No`TEXT,`Name`TEXT,`DOB`TEXT,`Address`TEXT,`Parent_Mobile_Number`TEXT,`Standard`TEXT,`Division`TEXT)";

    public static final int DATABASE_VERSION = 1;
    private static DbHelper dbInstance = null;
    private static SQLiteDatabase db;

    private Context _ctxt;

    //execute db string


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this._ctxt = context;
    }

    static synchronized DbHelper getInstance(Context ctx) {

        if (dbInstance == null) {
            dbInstance = new DbHelper(ctx);
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "In on create db");
       /* db.execSQL(strM_Parameter);
        db.execSQL(strdirect_lead_category_dtls);
        db.execSQL(strDirect_lead);
        db.execSQL(strdirect_lead_st_dtls);
        db.execSQL(strM_Category);
        db.execSQL(strUserDetail);*/
    }

    // Open the database connection.
    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void close() {
        try {
            if (db != null && db.isOpen())
                db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        onCreate(db);
    }

    void closeCursor(Cursor cursor) {
        try {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContentValues createContentValues(String values[], String names[]) {
        ContentValues values1 = null;
        try {
            values1 = new ContentValues();

            Log.e("name.length", String.valueOf(names.length));
            Log.e("values.length", String.valueOf(values.length));

            for (int i = 0; i < names.length; i++) {
                try {
                    if ((names[i].equalsIgnoreCase(_ctxt.getString(R.string.column_studentDetails)))) {
                        try {
                            int value = Integer.parseInt(values[i]);
                            values1.put(names[i], value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        values1.put(names[i], values[i]);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values1;
    }

    private ContentValues createContentValues1(String[] values, String names[]) {
        Log.e("abc", "inside create content values");
        ContentValues values1 = null;
        try {
            values1 = new ContentValues();

            Log.e("Abc", "length" + names.length);


            for (int i = 0; i < names.length; i++) {

                String valueArray = values[i];
                Log.e("abc", "$$-->" + valueArray);
              /*  String nameArray1=names[i];
                Log.e("Prachi","inserted names"+nameArray1);*/
                values1.put(names[i], values[i]);
                Log.v("abc", "value inserted");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values1;
    }

    long insert1(String values[], String names[], String tbl) {
        if (db != null && !db.isOpen())
            open();
        for (int i = 0; i < values.length; i++) {
            String values1 = values[i];
            Log.e("abc", "-->" + values1);
        }

        for (int i = 0; i < names.length; i++) {
            String names1 = names[i];
            Log.e("abc", "values" + names1);
        }

        ContentValues initialValues = createContentValues1(values, names);
        long inserted = 0;
        try {
            inserted = db.insert(tbl, null, initialValues);
            Log.e("abc", "values inserted" + inserted);
        } catch (Exception e) {
        }
        return inserted;
    }

    long insert(String values[], String names[], String tbl) {
        if (db != null && !db.isOpen())
            open();

        ContentValues initialValues = createContentValues(values, names);
        long inserted = 0;
        try {
            inserted = db.insert(tbl, null, initialValues);
        } catch (Exception e) {
        }
        return inserted;
    }

    Cursor fetch(String tbl, String names[], String where, String args[], String order, String limit,
                 boolean isDistinct, String groupBy, String having) {

        if (db != null && !db.isOpen())
            open();
        //Cursor cur = null;
        //try {
        return db.query(true, tbl, names, where, args, groupBy, having, order, limit);
        /*} catch (Exception e) {
            return null;
        }*/
    }

    Cursor fetchLastRow(String tbl, String orderByCol, String args[]) {

        if (db != null && !db.isOpen())
            open();
        String where = null;
        String limit = "1";
        String order = orderByCol + " DESC";
        String groupBy = null;
        String having = null;
        String names[] = null;

       /* SELECT *
                FROM food_table
        ORDER BY _id DESC
        LIMIT 1*/

        //Cursor cur = null;
        //try {
        return db.query(false, tbl, names, where, args, groupBy, having, order, limit);
        /*} catch (Exception e) {
            return null;
        }*/
    }

    public Cursor fetchallSpecify(String tbl, String names[], String fName,
                                  String fValue, String order) {
        if (db != null && !db.isOpen())
            open();
        Cursor mCursor = db.query(true, tbl, names, fName + "= '"
                + fValue + "'", null, null, null, order, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    Cursor rawQuery(String query) {

        if (db != null && !db.isOpen())
            open();
        //Cursor cur = null;
        //try {
        return db.rawQuery(query, null);
        /*} catch (Exception e) {
            return null;
        }*/
    }

    boolean delete(String tbl, String where, String args[]) {

        if (db != null && !db.isOpen())
            open();

        boolean isDeleted = false;
        try {
            isDeleted = db.delete(tbl, where, args) > 0;
        } catch (Exception e) {
        }
        return isDeleted;
    }

    boolean update(String where, String values[], String names[], String tbl, String args[]) {

        if (db != null && !db.isOpen())
            open();

        ContentValues updateValues = createContentValues(values, names);

        boolean isUpdated = false;
        try {
            isUpdated = db.update(tbl, updateValues, where, args) > 0;

            if (!isUpdated) {
                long result = db.insert(tbl, null, updateValues);
                if (result > 0) {
                    isUpdated = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }



    boolean alterTable(String tbl) {
        boolean isAlter = false;
        try {
            if (db != null && !db.isOpen())
                open();
            db.execSQL("DELETE FROM " + tbl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlter;
    }


    boolean updateBulk(String where, String values[], String names[], String tbl, String args[]) {

        if (db != null && !db.isOpen())
            open();
        ContentValues updateValues = createContentValues(values, names);
        Log.d("Update Query=>", updateValues.toString());

        boolean isUpdated = false;
        try {
            isUpdated = db.update(tbl, updateValues, where, args) > 0;

            if (!isUpdated) {

                long result = db.insert(tbl, null, updateValues);

                if (result > 0) {
                    isUpdated = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    public SQLiteStatement beginDBTransaction(String tableName, String names[]) {
        SQLiteStatement statement = null;
        try {
            if (db != null && !db.isOpen())
                open();
            String values = "";
            for (int i = 0; i < names.length; i++) {
                values = values + "?,";
            }

            if (values != null && values.length() > 0 && !(values.equalsIgnoreCase(""))) {
                values = values.substring(0, values.lastIndexOf(","));
            }
            String sql = "INSERT INTO " + tableName + " VALUES (" + values + ")";
            statement = db.compileStatement(sql);
            db.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public void beginDBTransaction() {

        try {
            if (db != null && !db.isOpen())
                open();

            db.beginTransaction();
            Log.i("DB_APP", "In beginDBTransaction");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void endDBTransaction() {
        try {
            if (db == null || (db != null && !db.isOpen()))
                open();


            db.endTransaction();
            Log.i("DB_APP", "In endDBTransaction");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbTransactionSucessFull() {
        try {
            if (db != null && !db.isOpen())
                open();
            db.setTransactionSuccessful();
            Log.i("DB_APP", "In dbTransactionSucessFull");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateServerStatus(String status) {
        try {
            if (db != null && !db.isOpen())
                open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getCountOfRows(String table, String whereClause, String[] whereArgs) {
        if (db != null && !db.isOpen())
            if (db != null && !db.isOpen())
                open();
        return DatabaseUtils.queryNumEntries(db, table, whereClause, whereArgs);
    }

    public long getCountOfRows(String tableName) {

        if (db != null && !db.isOpen())
            open();
        return DatabaseUtils.queryNumEntries(db, tableName);
    }

}
