package com.example.club.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOError;
import java.io.IOException;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "ClubSystem.db"; // Database Name

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_User_Table = "create table User(username TEXT NOT NULL, password TEXT NOT NULL, emailAddress TEXT NOT NULL" +
                ", year INTEGER, type INTEGER NOT NULL, gender TEXT,avatar BLOB, PRIMARY KEY(username), FOREIGN KEY(username) REFERENCES images(id))";
        String Insert_User_Table = "insert into User(username,password,emailAddress,year,type,gender) values(?,?,?,?,?,?)";


        String Create_ClubAccount_Table = "CREATE TABLE ClubAccount (clubid TEXT NOT NULL,password TEXT NOT NULL,emailaddress TEXT NOT NULL," +
                "type TEXT NOT NULL,FOREIGN KEY(clubid) REFERENCES FollowingClub(user_userid),PRIMARY KEY(clubid))";
        String Create_Activity_Table = "CREATE TABLE Activity (activityid TEXT,postByType INTEGER,postUserID TEXT,activityName TEXT," +
                "location TEXT,date TEXT,time TEXT,type TEXT,description TEXT,capacity INTEGER,img BLOB,likes INTEGER, detailsurl TEXT,FOREIGN KEY(img) REFERENCES Image(img)," +
                "FOREIGN KEY(activityid) REFERENCES ClubAccount(clubid),PRIMARY KEY(activityid))";

        String Create_FollowingClub_Table = "CREATE TABLE FollowingClub (user_userid TEXT,club_userid TEXT,FOREIGN KEY(club_userid) REFERENCES ClubAccount(clubid),FOREIGN KEY(user_userid) REFERENCES User(username),PRIMARY KEY(user_userid,club_userid))";
        String Create_Comment_Table = "CREATE TABLE Comment (commentid TEXT,belongactivityid TEXT,postuserid TEXT, content TEXT NOT NULL, FOREIGN KEY(belongactivityid) REFERENCES Activity(activityid),FOREIGN KEY(postuserid) REFERENCES User(username),PRIMARY KEY(commentid,postuserid,belongactivityid))";

        db.execSQL("create table images(id TEXT primary key, img blob not null)");
        db.execSQL(Create_Activity_Table);
        db.execSQL(Create_User_Table);
        db.execSQL(Create_ClubAccount_Table);

        db.execSQL(Create_FollowingClub_Table);
        db.execSQL(Create_Comment_Table);


        db.execSQL(Insert_User_Table, new Object[]{"Yuqi.Guo17", "1234", "Yuqi.Guo17@studentt.xjtlu.edu.cn", 4, 3, "male"});

        inital(db);
    }

    private void inital(SQLiteDatabase db){

        /////////////////////////// Insert Activities /////////////////////////////////
        String Insert_Activity_Table = "insert into Activity(activityid,postByType,postUserID,activityName,location,date,time,type,description,capacity,img,likes,detailsurl) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Bitmap bitmap1 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/lecture1.jpg"));
        byte[] activityPhoto1 = Bitmap2Bytes(bitmap1);
        db.execSQL(Insert_Activity_Table, new Object[]{"001",1,"Math Club","Final review lectures","CB","2020-12-10","12:00","Lecture","",0,activityPhoto1,0,""});

        Bitmap bitmap2 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/lecture2.jpg"));
        byte[] activityPhoto2 = Bitmap2Bytes(bitmap2);
        db.execSQL(Insert_Activity_Table, new Object[]{"002",1,"Design Association","Architecture Workshop","SD","2020-12-12","13:00","Lecture","",130,activityPhoto2,10,""});

        Bitmap bitmap3 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/game1.jpg"));
        byte[] activityPhoto3 = Bitmap2Bytes(bitmap3);
        db.execSQL(Insert_Activity_Table, new Object[]{"003",1,"Volleyball Club","Weekly volleyball activity","CB","2020-12-10","12:00","Game","",100,activityPhoto3,10,""});

        Bitmap bitmap4 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/game2.jpg"));
        byte[] activityPhoto4 = Bitmap2Bytes(bitmap4);
        db.execSQL(Insert_Activity_Table, new Object[]{"004",1,"Basketball Team","Regular training","CB","2020-12-10","12:00","Game","",100,activityPhoto4,10,""});

        Bitmap bitmap5 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/match1.jpg"));
        byte[] activityPhoto5 = Bitmap2Bytes(bitmap5);
        db.execSQL(Insert_Activity_Table, new Object[]{"005",1,"Football Club","Football Match","Gym","2020-12-10","12:00","Match","",100,activityPhoto5,10,""});

        Bitmap bitmap6 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/match2.jpg"));
        byte[] activityPhoto6 = Bitmap2Bytes(bitmap6);
        db.execSQL(Insert_Activity_Table, new Object[]{"006",1,"Softball Club","7th Friendly Match","Playground","2020-12-10","12:00","Match","",100,activityPhoto6,10,""});

        Bitmap bitmap7 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/party1.jpg"));
        byte[] activityPhoto7 = Bitmap2Bytes(bitmap7);
        db.execSQL(Insert_Activity_Table, new Object[]{"007",1,"Product Creating Troupe","Troupe and Tea Party","CB","2020-12-10","12:00","Party","",100,activityPhoto7,10,""});

        Bitmap bitmap8 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/party2.jpg"));
        byte[] activityPhoto8 = Bitmap2Bytes(bitmap8);
        db.execSQL(Insert_Activity_Table, new Object[]{"008",1,"Volunteer Academy","Volunteer Annual Party","CB","2020-12-10","12:00","Party","",100,activityPhoto8,10,""});


        /////////////////////////// Insert Clubs /////////////////////////////////
        String Insert_Club_Table = "insert into ClubAccount(clubid,password,emailaddress,type) values(?,?,?,?)";
        String Insert_User_Table = "insert into User(username,password,emailAddress,year,type,gender) values(?,?,?,?,?,?)";
        String Insert_Image_Table = "insert into images(id,img) values(?,?)";

        db.execSQL(Insert_Club_Table, new Object[]{"Volunteer Academy","1234","VolunteerAcademy@xjtlu.edu.cn","Functional Organizations"});
        db.execSQL(Insert_User_Table, new Object[]{"Volunteer Academy","1234","VolunteerAcademy@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp1 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/party2.jpg"));
        byte[] clubPhoto1 = Bitmap2Bytes(bp1);
        db.execSQL(Insert_Image_Table, new Object[]{"Volunteer Academy",clubPhoto1});

        db.execSQL(Insert_Club_Table, new Object[]{"Math Club","1234","Math@xjtlu.edu.cn","Academic Clubs"});
        db.execSQL(Insert_User_Table, new Object[]{"Math Club","1234","Math@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp2 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/dance.png"));
        byte[] clubPhoto2 = Bitmap2Bytes(bp2);
        db.execSQL(Insert_Image_Table, new Object[]{"Math Club",clubPhoto2});

        db.execSQL(Insert_Club_Table, new Object[]{"Astronomy","1234","Math@xjtlu.edu.cn","Academic Clubs"});
        db.execSQL(Insert_User_Table, new Object[]{"Astronomy","1234","Math@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp3 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/astronomy.jpg"));
        byte[] clubPhoto3 = Bitmap2Bytes(bp3);
        db.execSQL(Insert_Image_Table, new Object[]{"Astronomy",clubPhoto3});

        db.execSQL(Insert_Club_Table, new Object[]{"Badminton","1234","Badminton@xjtlu.edu.cn","Sports Clubs"});
        db.execSQL(Insert_User_Table, new Object[]{"Badminton","1234","Badminton@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp4 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/badminton.jpg"));
        byte[] clubPhoto4 = Bitmap2Bytes(bp4);
        db.execSQL(Insert_Image_Table, new Object[]{"Badminton",clubPhoto4});

        db.execSQL(Insert_Club_Table, new Object[]{"Chorus","1234","Chorus@xjtlu.edu.cn","Art Organizations & Clubs"});
        db.execSQL(Insert_User_Table, new Object[]{"Chorus","1234","Chorus@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp5 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/chorus.jpg"));
        byte[] clubPhoto5 = Bitmap2Bytes(bp5);
        db.execSQL(Insert_Image_Table, new Object[]{"Chorus",clubPhoto5});

        db.execSQL(Insert_Club_Table, new Object[]{"Ban Xue","1234","BanXue@xjtlu.edu.cn","Academic Clubs"});
        db.execSQL(Insert_User_Table, new Object[]{"Ban Xue","1234","BanXue@xjtlu.edu.cn",0,2,"male"});
        Bitmap bp6 = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/banxueshe.jpg"));
        byte[] clubPhoto6 = Bitmap2Bytes(bp6);
        db.execSQL(Insert_Image_Table, new Object[]{"Ban Xue",clubPhoto6});

    }

    private byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insertImage(String x, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] imgbyte = new byte[fs.available()];

            fs.read(imgbyte);

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("img", imgbyte);

            db.insert("images", null, contentValues);
            fs.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertDefultImage(String id, byte[] imgb) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("img", imgb);

            db.insert("images", null, contentValues);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }

    public boolean amendImage(String id, byte[] imgb) {
        SQLiteDatabase db = this.getWritableDatabase();

       try{
           db.execSQL("delete from images where id=?",new Object[]{id});

           ContentValues contentValues = new ContentValues();
           contentValues.put("id",id);
           contentValues.put("img",imgb);

           db.insert("images", null, contentValues);

           return true;
       } catch (Exception e) {
           ContentValues contentValues = new ContentValues();
           contentValues.put("id",id);
           contentValues.put("img",imgb);

           db.insert("images", null, contentValues);

           return true;
       }

    }

    public boolean amendImage(String x, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] imgbyte = new byte[fs.available()];

            fs.read(imgbyte);

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("img", imgbyte);

            db.update("images", contentValues, "id=?", new String[]{id});
            fs.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public Bitmap getImage(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from images where id=?", new String[]{String.valueOf(id)});
        AbstractWindowedCursor ac = (AbstractWindowedCursor) cursor;
        if (ac.moveToFirst()) {
            byte[] img = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(img, 0, img.length);
        }

        return bt;
    }


}
