package com.example.club.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.club.Objects.Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ManageActivityImple {
    private DatabaseHelper helper;

    public ManageActivityImple(Context context){
        helper = new DatabaseHelper(context);
    }

    public boolean insert(Activity activity) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        activity.getImage().compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        values.put("activityid",activity.getActivityID());
        values.put("postByType",activity.getPostByType());
        values.put("postUserID",activity.getPostUserID());
        values.put("activityName",activity.getActivityName());
        values.put("location",activity.getLocation());
        values.put("time",activity.getTime());
        values.put("type",activity.getType());
        values.put("description",activity.getDescription());
        values.put("capacity",activity.getCapacity());

        values.put("img",data);

        values.put("likes",activity.getLikes());
        values.put("detailsurl",activity.getDetailsURL());

        try {
            db.insert("Activity",null,values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Activity> queryAll() {
        ArrayList<Activity> result = new ArrayList<Activity>();

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from Activity ", null);

        while (cursor.moveToNext()) {
            Activity activity = new Activity("",0,"","","","","","","",0,null,0,"");

            byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
            Bitmap bt = BitmapFactory.decodeByteArray(img,0,img.length);

            activity.setActivityID(cursor.getString(cursor.getColumnIndex("activityid")));
            activity.setPostByType(cursor.getInt(cursor.getColumnIndex("postByType")));
            activity.setPostUserID(cursor.getString(cursor.getColumnIndex("postUserID")));
            activity.setActivityName(cursor.getString(cursor.getColumnIndex("activityName")));
            activity.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            activity.setTime(cursor.getString(cursor.getColumnIndex("time")));
            activity.setType(cursor.getString(cursor.getColumnIndex("type")));
            activity.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            activity.setCapacity(cursor.getInt(cursor.getColumnIndex("capacity")));

            activity.setImage(bt);

            activity.setLikes(cursor.getInt(cursor.getColumnIndex("likes")));
            activity.setDetailsURL(cursor.getString(cursor.getColumnIndex("detailsurl")));

            result.add(activity);
        }

        return result;
    }

    public ArrayList<Activity> query(String userID) {
        Activity activity = new Activity("",0,"","","","","","","",0,null,0,"");
        ArrayList<Activity> result = new ArrayList<Activity>();

        SQLiteDatabase sdb = helper.getReadableDatabase();

        String sql = "select * from Activity where postUserID=?";

        Cursor cursor = sdb.rawQuery(sql, new String[] {userID});

        if(cursor!=null&&cursor.moveToFirst()){

            do{
                byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
                Bitmap bt = BitmapFactory.decodeByteArray(img,0,img.length);

                activity.setActivityID(cursor.getString(cursor.getColumnIndex("activityid")));
                activity.setPostByType(cursor.getInt(cursor.getColumnIndex("postByType")));
                activity.setPostUserID(cursor.getString(cursor.getColumnIndex("postUserID")));
                activity.setActivityName(cursor.getString(cursor.getColumnIndex("activityName")));
                activity.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                activity.setTime(cursor.getString(cursor.getColumnIndex("time")));
                activity.setType(cursor.getString(cursor.getColumnIndex("type")));
                activity.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                activity.setCapacity(cursor.getInt(cursor.getColumnIndex("capacity")));

                activity.setImage(bt);

                activity.setLikes(cursor.getInt(cursor.getColumnIndex("likes")));
                activity.setDetailsURL(cursor.getString(cursor.getColumnIndex("detailsurl")));

                result.add(activity);
            }while(cursor.moveToNext());

        }

        cursor.close();

        return result;
    }

    public Activity search(String activityID) {
        Activity activity = new Activity("",0,"","","","","","","",0,null,0,"");

        SQLiteDatabase sdb = helper.getReadableDatabase();
        String sql = "select * from Activity where activityid=?";
        Cursor cursor = sdb.rawQuery(sql, new String[] {activityID});

        if(cursor!=null&&cursor.moveToFirst()){

            do{
                byte[] img = cursor.getBlob(cursor.getColumnIndex("img"));
                Bitmap bt = BitmapFactory.decodeByteArray(img,0,img.length);

                activity.setActivityID(cursor.getString(cursor.getColumnIndex("activityid")));
                activity.setPostByType(cursor.getInt(cursor.getColumnIndex("postByType")));
                activity.setPostUserID(cursor.getString(cursor.getColumnIndex("postUserID")));
                activity.setActivityName(cursor.getString(cursor.getColumnIndex("activityName")));
                activity.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                activity.setTime(cursor.getString(cursor.getColumnIndex("time")));
                activity.setType(cursor.getString(cursor.getColumnIndex("type")));
                activity.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                activity.setCapacity(cursor.getInt(cursor.getColumnIndex("capacity")));

                activity.setImage(bt);

                activity.setLikes(cursor.getInt(cursor.getColumnIndex("likes")));
                activity.setDetailsURL(cursor.getString(cursor.getColumnIndex("detailsurl")));

            }while(cursor.moveToNext());

        }

        cursor.close();

        return activity;
    }

    public boolean delete(String activityID) {
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            db.delete("Activity", "activityid" + "=?", new String[]{activityID});
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean update(Activity activity) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        activity.getImage().compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        values.put("activityid",activity.getActivityID());
        values.put("postByType",activity.getPostByType());
        values.put("postUserID",activity.getPostUserID());
        values.put("activityName",activity.getActivityName());
        values.put("location",activity.getLocation());
        values.put("time",activity.getTime());
        values.put("type",activity.getType());
        values.put("description",activity.getDescription());
        values.put("capacity",activity.getCapacity());

        values.put("img",data);

        values.put("likes",activity.getLikes());
        values.put("detailsurl",activity.getDetailsURL());

        try {
            db.update("Activity", values, "activityid",new String[]{activity.getActivityID()});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  JSONArray getResults(String myTable)
    {
        SQLiteDatabase myDataBase = helper.getWritableDatabase();

        String searchQuery = "SELECT  * FROM " + myTable;
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        } else {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                       e.printStackTrace();
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();

        return resultSet;
    }
}
