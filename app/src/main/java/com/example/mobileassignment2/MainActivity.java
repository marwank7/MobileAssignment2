package com.example.mobileassignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "Name";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static final String GENDER = "Gender";
    public static final String FLAG = "Flag";

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEdt;

    private EditText Name;
    private EditText Height;
    private EditText Weight;
    private Spinner Gender;
    private EditText Result;
    private CheckBox Chkbox;
    private Button btnAct2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.nameField);
        Height = findViewById(R.id.heightField);
        Weight = findViewById(R.id.weightField);
        Gender = findViewById(R.id.gender);
        populateSpinner();

        Result = findViewById(R.id.result);

        Chkbox = findViewById(R.id.remember);

        btnAct2 = findViewById(R.id.btnAct);

        setupSharedPrefs();
        checkPrefs();


    }

    private void populateSpinner(){
        String[] genres = {"", "Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, genres);

        Gender.setAdapter(adapter);
    }

    private double calculateBMI(double height, double weight){
        double BMI = 0;
        height = height / 100;
        BMI = weight/(height*height);
        return BMI;
    }


    public void btnOnClick(View view) {

        String name = Name.getText().toString();
        String weight = Weight.getText().toString();
        String height = Height.getText().toString();
        int gender = Gender.getSelectedItemPosition();

        if(Chkbox.isChecked()){
            prefsEdt.putString(NAME, name);
            prefsEdt.putString(HEIGHT, height);
            prefsEdt.putString(WEIGHT, weight);
            prefsEdt.putInt(GENDER, gender);
            prefsEdt.putBoolean(FLAG, true);
            prefsEdt.commit();
        }

        double BMI = calculateBMI(Double.parseDouble(Height.getText().toString()), Double.parseDouble(Weight.getText().toString()));
        String str = Double.toString(BMI);

        Result.setText(str);
    }

    private void checkPrefs(){
        boolean flag = prefs.getBoolean(FLAG, false);

        if(flag){
            String name = prefs.getString(NAME, "");
            String height = prefs.getString(HEIGHT, "");
            String weight = prefs.getString(WEIGHT, "");
            int gender = prefs.getInt(GENDER, 0);

            Name.setText(name);
            Height.setText(height);
            Weight.setText(weight);
            Gender.setSelection(gender);
            Chkbox.setChecked(true);
        }
    }

    private void setupSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEdt = prefs.edit();
    }


    public void btnNextAct(View view){
        Intent intent = new Intent(this, MainActivity2.class);

        startActivity(intent);
    }

}