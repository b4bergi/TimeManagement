package com.example.dberghammer.timemanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Runnable{
    Socket socket;
    String ip="10.10.107.24";
    int port=1234;
    BufferedWriter bw;
    BufferedReader br;
    String gruppe="gruppe";
    Event e=null;

    SQLiteDatabase db;
    private static SimpleCursorAdapter cursorAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


        try {
            socket=new Socket(ip, port);
            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

          
        } catch (IOException e) {
            Log.e("----", "Fehler try");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuAdd:
                add();
                break;
            case R.id.menuChooseGroup:
                break;
            case R.id.menuGroupUpdate:
                refresh();
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        try {
            Thread t=new Thread(this);
            t.start();

            bw.write("refresh:gruppe \r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        try {
            Intent i=new Intent(this, addEventClass.class);
            startActivityForResult(i,123 );

           bw.write("add:"+e.getName()+";"+e.getD()+";"+e.getTagev()+";"+e.getNotiz()+":"+gruppe+" \r\n");
            bw.flush();
        } catch (IOException ex) {
           ex.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        try {
            socket=new Socket(ip, port);
            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));



        } catch (IOException e) {
            Log.e("----", "Fehler try");
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       Bundle extras =data.getExtras();
        String name=extras.getString("name");
        Date date=(Date)data.getSerializableExtra("date");
        int tagev=extras.getInt("tagev");
        String note=extras.getString("note");
        e=new Event(note,name,date,tagev);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        try {
            socket.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public void run() {

        while (true) {

            try {
                Date d=null;
                String ev=br.readLine();
                String []events=ev.split(":");
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                try {
                     d= sdf.parse(events[2]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Event e=new Event(events[0],events[1], d ,Integer.parseInt(events[3]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
