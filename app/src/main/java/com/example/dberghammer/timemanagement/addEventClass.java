package com.example.dberghammer.timemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dberghammer on 30.05.2016.
 */
public class addEventClass extends Activity {
    EditText name;
    DatePicker datePicker;
    NumberPicker numberPicker;
    EditText notiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        name=(EditText)findViewById(R.id.textInput);
        datePicker=(DatePicker)findViewById(R.id.Datepicker);
        numberPicker=(NumberPicker)findViewById(R.id.numberPicker);
        notiz=(EditText)findViewById(R.id.note);
    }

    public void buttonClicked(View view){
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

        String sname=name.getText().toString();
        String sdate=(datePicker.getDayOfMonth()+"-"+datePicker.getFirstDayOfWeek()+"-"+datePicker.getFirstDayOfWeek());
        int tagev= numberPicker.getValue();
        String snotiz= notiz.getText().toString();
        Date d=null;
        try {
            d=sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event e=new Event(snotiz,sname,d,tagev);
        Intent intent =new Intent();

        intent.putExtra("name",sname);
        intent.putExtra("date",d);
        intent.putExtra("tagev",tagev);
        intent.putExtra("note",snotiz);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
