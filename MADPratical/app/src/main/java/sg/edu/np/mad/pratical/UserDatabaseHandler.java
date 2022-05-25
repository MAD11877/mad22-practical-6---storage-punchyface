package sg.edu.np.mad.pratical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabaseHandler extends SQLiteOpenHelper{
    public static String DATABASE_NAME = "UserDB";
    public static Integer DATABASE_VERSION = 1;
    public static String TABLE_PROFILE = "Profile";
    public static String COLUMN_USERNAME = "username";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "followed";
    public static String[] COLUMNS = {COLUMN_USERNAME, COLUMN_DESCRIPTION, COLUMN_ID, COLUMN_FOLLOWED};


    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERDB =
                "CREATE TABLE " + TABLE_PROFILE +"("  +
                        COLUMN_USERNAME + " TEXT NOT NULL," +
                        COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUMN_FOLLOWED+ " TEXT NOT NULL," +
                        "UNIQUE (" + COLUMN_USERNAME + ") ON CONFLICT ABORT)";
        db.execSQL(CREATE_USERDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        this.onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public User getUser(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILE,
                COLUMNS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if (cursor != null){
            cursor.moveToFirst();
        }

        User user = new User(cursor.getString(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3) == 1 ? true: false);
        return user;
    }

    public int updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.update(TABLE_PROFILE,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        db.close();
        return i;
    }

    public ArrayList<User> allUser(){
        ArrayList<User> userlist = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PROFILE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;
        if (cursor.moveToFirst()){
            do {
                user = new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3) == 1 ? true: false);
                userlist.add(user);
            }while (cursor.moveToNext());
        }
        return  userlist;
    }
}

