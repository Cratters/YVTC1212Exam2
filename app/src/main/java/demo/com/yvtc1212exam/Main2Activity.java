package demo.com.yvtc1212exam;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    public static ArrayList idArray = new ArrayList();
    public static ArrayList<String> stitleArray = new ArrayList<>();
    public static ArrayList<Double> longitudeArray = new ArrayList<>();
    public static ArrayList<Double> latitudeArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DBInfo.DB_FILE = getDatabasePath("mymap")+".db";    //database的絕對路徑
        copyDBFile();

        getData();
    }

    public void onOne (View v) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onTwo (View v) {
        Intent intent = new Intent(Main2Activity.this, FireChat.class);
        startActivity(intent);
    }

    public void onThree (View v) {
        Intent intent = new Intent(Main2Activity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void getData(){
        MyDBHelper helper = MyDBHelper.getInstance(this);
        Cursor c = helper.getReadableDatabase().query("mymap", null, null, null, null, null, null);

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            int id = c.getInt(c.getColumnIndex("_id"));
            String stitle = c.getString(c.getColumnIndex("標題"));
            double longitude = c.getDouble(c.getColumnIndex("經度"));
            double latitude = c.getDouble(c.getColumnIndex("緯度"));
            idArray.add(id);
            stitleArray.add(stitle);
            longitudeArray.add(longitude);
            latitudeArray.add(latitude);

            c.moveToNext();
        }
        c.close();
    }


    public void copyDBFile()
    {
        try {
            File f = new File(DBInfo.DB_FILE);
            File dbDir = new File(DBInfo.DB_FILE.substring(0,DBInfo.DB_FILE.length()-9));
            Log.d("MyDBHelper", "copyFiles : "+DBInfo.DB_FILE);
            dbDir.mkdirs();
            if (! f.exists())
            {

                InputStream is = getResources().openRawResource(R.raw.mymap);
                OutputStream os = new FileOutputStream(DBInfo.DB_FILE);
                int read;
                Log.d("taipeiviews.db", "Start Copy");
                while ((read = is.read()) != -1)
                {
                    os.write(read);
                }
                Log.d("taipeiviews.db", "FilesCopied");
                os.close();
                is.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
