package com.example.dberghammer.timemanagement;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    String ip="10.10.107.24";
    int port=1234;
    BufferedWriter bw;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


        try {
            socket=new Socket(ip, port);
            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


          
        } catch (IOException e) {
            Log.e("----", "Fehler try");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuAdd:
                testMethode();
                break;
            case R.id.menuChooseGroup:
                break;
            case R.id.menuGroupUpdate:
                try {
                    Log.v("++++", "refresh log");
                    bw.write("refresh:gruppe \r\n");
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void testMethode() {


        try {



           bw.write("add:name;datum;tagev;notiz:gruppe \r\n");



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
    protected void onPause() {
        try {
            socket.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }
}
