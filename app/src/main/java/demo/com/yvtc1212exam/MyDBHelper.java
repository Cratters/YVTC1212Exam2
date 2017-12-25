package demo.com.yvtc1212exam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by auser on 2017/12/12.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static MyDBHelper instance = null;
    public static MyDBHelper getInstance(Context ctx){
        Log.d("MyDBHelper","getInstance");
        if(instance == null){
            instance = new MyDBHelper(ctx, "mymap.db", null, 1);
        }
        return instance;
    }

    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("MyDBHelper","onCreate DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
