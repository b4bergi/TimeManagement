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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    String ip="10.0.0.131";
    int port=1234;
    BufferedWriter bw;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:


                testMethode();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


        try {
            socket=new Socket(ip, port);


          
        } catch (IOException e) {
            Log.e("----", "Fehler try");
            e.printStackTrace();
        }

    }
    private void testMethode() {


        try {



            bw.write("add:asddfa;df \r\n");

            bw.flush();
        } catch (IOException ex) {
           ex.printStackTrace();
        }

    }
}
