package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.age);
        textView = findViewById(R.id.textView3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    private void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if(year<=cal.get(Calendar.YEAR) && month<=cal.get(Calendar.MONTH) && day<=cal.get(Calendar.DATE)){
                    LocalDate dob = LocalDate.of(year,month+1,day);
                    LocalDate currDate = LocalDate.now();
                    Period period = Period.between(dob,currDate);
                    int x = period.getYears();
                    int y = period.getMonths();
                    int z = period.getDays();
                    String a = "Your age is:\n"+x+" years\n"+y+" months\n"+z+" days";
                    textView.setText(a);
                }
                else{
                    Toast.makeText(MainActivity.this, "The Date can't be greater than Current Date", Toast.LENGTH_SHORT).show();
                }
            }
        },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
        dialog.show();
    }
}