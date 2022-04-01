package edu.aaabuk02.courselogger;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;

public class CourseDataSource {

    private SQLiteDatabase database;
    private CourseDBHelper dbHelper;

    public CourseDataSource(Context context) {
        dbHelper = new CourseDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertCourseContact(CourseContact c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("coursename", c.getCourseName());
            initialValues.put("coursedesc", c.getCourseDesc());
            initialValues.put("studyhour", c.getStudyHourTarget());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());

            didSucceed = database.insert("courseContact", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateCourseContact(CourseContact c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getCourseContactID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("coursename", c.getCourseName());
            updateValues.put("coursedesc", c.getCourseDesc());
            updateValues.put("studyhour", c.getStudyHourTarget());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());

            didSucceed = database.update("courseContact", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastCourseContactId() {
        int lastId = -1;
        try {
            String query = "Select MAX(_id) from courseContact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public boolean insertDeadlineInfoContact(DeadlineInfoContact d) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("courseContactID", d.getCourseContactID());
            initialValues.put("deadlinedate", d.getDeadlineDate());
            initialValues.put("deadlineinfo", d.getDeadlineInfo());

            didSucceed = database.insert("deadlineInfoContact", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateDeadlineInfoContact(DeadlineInfoContact d) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) d.getDeadLineContactInfoID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("courseContactID", d.getCourseContactID());
            updateValues.put("deadlinedate", d.getDeadlineDate());
            updateValues.put("deadlineinfo", d.getDeadlineInfo());

            didSucceed = database.update("deadlineInfoContact", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastDeadlineInfoContactId() {
        int lastId = -1;
        try {
            String query = "Select MAX(_id) from dishContact";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<String> getCourseName() {
        ArrayList<String> dishNames = new ArrayList<>();
        try {
            String query = "Select dishname from dishContact";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dishNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            dishNames = new ArrayList<String>();
        }
        return dishNames;
    }

    public ArrayList<CourseContact> getCourseContacts(){
        ArrayList<CourseContact> courseContacts = new ArrayList<CourseContact>();
        try{
            String query = "SELECT * FROM courseContact";
            Cursor cursor = database.rawQuery(query, null);

            CourseContact newCourseContact;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                newCourseContact = new CourseContact();

                newCourseContact.setCourseContactID(cursor.getInt(0));
                newCourseContact.setCourseName(cursor.getString(1));
                newCourseContact.setCourseDesc(cursor.getString(2));
                newCourseContact.setStudyHourTarget(cursor.getString(3));
                courseContacts.add(newCourseContact);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e)
        {
            courseContacts = new ArrayList<CourseContact>();
        }
        return courseContacts;
    }





    public CourseContact getSpecificContact(int courseContactID) {
        CourseContact courseContact = new CourseContact();
        String query = "SELECT * FROM courseContact WHERE _id =" + courseContactID;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            courseContact.setCourseContactID(cursor.getInt(0));
            courseContact.setCourseName(cursor.getString(1));
            courseContact.setCourseDesc(cursor.getString(2));
            courseContact.setStudyHourTarget(cursor.getString(3));
            cursor.close();
        }

        return courseContact;
    }

    public boolean deleteCourseContact(int courseContactID) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("courseContact", "_id=" + courseContactID, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }


    //Deadline

    public DeadlineInfoContact getSpecificContactDeadline(int deadlineContactID) {
        DeadlineInfoContact deadlineInfoContact = new DeadlineInfoContact();
        String query = "SELECT * FROM deadlineInfoContact WHERE _id =" + deadlineContactID;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            deadlineInfoContact.setDeadLineContactInfoID(cursor.getInt(0));
            deadlineInfoContact.setCourseContactID(cursor.getInt(1));
            deadlineInfoContact.setDeadlineDate(cursor.getString(2));
            deadlineInfoContact.setDeadlineInfo(cursor.getString(3));
            cursor.close();
        }

        return deadlineInfoContact;
    }

    public boolean deleteDeadlineInfoContact(int deadlineContactID) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("deadlineInfoContact", "_id=" + deadlineContactID, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }

    public ArrayList<String> getDeadlineName() {
        ArrayList<String> deadlineNames = new ArrayList<>();
        try {
            String query = "Select deadlineinfo from deadlineInfoContact";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                deadlineNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            deadlineNames = new ArrayList<String>();
        }
        return deadlineNames;
    }

    public ArrayList<DeadlineInfoContact> getDeadlineContacts(){
        ArrayList<DeadlineInfoContact> deadlineContacts = new ArrayList<DeadlineInfoContact>();
        try{
            String query = "SELECT * FROM deadlineInfoContact";
            Cursor cursor = database.rawQuery(query, null);

            DeadlineInfoContact newDeadlineInfoContact;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                newDeadlineInfoContact = new DeadlineInfoContact();

                newDeadlineInfoContact.setDeadLineContactInfoID(cursor.getInt(0));
                newDeadlineInfoContact.setCourseContactID(cursor.getInt(1));
                newDeadlineInfoContact.setDeadlineDate(cursor.getString(2));
                newDeadlineInfoContact.setDeadlineInfo(cursor.getString(3));
                deadlineContacts.add(newDeadlineInfoContact);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e)
        {
            deadlineContacts = new ArrayList<DeadlineInfoContact>();
        }
        return deadlineContacts;
    }
}
