package edu.aaabuk02.courselogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CourseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycourses.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_CONTACT_1 =
            "create table courseContact (_id integer primary key autoincrement, "
                    + "coursename text not null, coursedesc text, studyhour text, streetaddress text, "
                    + "city text, state text, zipcode text);";

    // Database creation sql statement
    private static final String CREATE_TABLE_CONTACT_2 =
            "create table deadlineInfoContact (_id integer primary key autoincrement, "
                    + "courseContactID text not null," + "deadlinedate text not null," + " deadlineinfo text);";

    public CourseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CONTACT_1);
        database.execSQL(CREATE_TABLE_CONTACT_2);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CourseDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS courseContact");
        db.execSQL("DROP TABLE IF EXISTS deadlineInfoContact");

        onCreate(db);
    }
}
