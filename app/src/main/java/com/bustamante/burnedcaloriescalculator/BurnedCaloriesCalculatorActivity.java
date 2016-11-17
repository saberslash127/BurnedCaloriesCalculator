package com.bustamante.burnedcaloriescalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class BurnedCaloriesCalculatorActivity extends Activity
        implements TextView.OnEditorActionListener, View.OnClickListener {

    private EditText inputWeight;
    private TextView ranResultText;
    private SeekBar seekBar;
    private TextView burnResultText;
    private Spinner spinner1;
    private Spinner spinner2;
    private TextView bmiResult;
    private TextView nameResult;

    // saves
    private SharedPreferences savedValues;

    // variable saved
    private String inputWeightString = "";
    private int milesString;
    private int feetString;
    private int inchString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burned_calories_calculator);

        //reference
        inputWeight = (EditText) findViewById(R.id.weightInput);
        ranResultText = (TextView) findViewById(R.id.ranResultsText);
        seekBar = (SeekBar) findViewById(R.id.ranSeekBar);
        burnResultText = (TextView) findViewById(R.id.burnResultText);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        bmiResult = (TextView) findViewById(R.id.bmiResultText);
        nameResult = (TextView) findViewById(R.id.nameResultText);

        // set array adapter for spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.spinner_1, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_2, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        seekBar.setOnClickListener(this);

        ranResultText.setText(seekBar.getProgress());

        inputWeight.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    calculateAndDisplay();
                }
                return false;
            }
        });
    }


    //@Override
    //public void onPause() {
        // save the instance variables
        //SharedPreferences.Editor editor = savedValues.edit();
        //editor.putString("inputWeightString", inputWeightString);
        //editor.putInt("milesString", milesString);
       // editor.putInt("feetString", feetString);
       // editor.putInt("inchString", inchString);
       // editor.commit();

      //  super.onPause();
    //}

    //Override
    //public void onResume() {
        //super.onResume();

        // get the instance variables
        //inputWeightString = savedValues.getString("inputWeightString", "");
        //milesString = savedValues.getInt("tipPercent", 0);

        // set the weight amount on its widget
        //inputWeight.setText(inputWeightString);

        // calculate and display
        //calculateAndDisplay();
    //}

    public void calculateAndDisplay() {

        // get the bill amount
        inputWeightString = inputWeight.getText().toString();
        float weight;

        if (inputWeightString.equals("")) {
            weight = 0;}
        else {
            weight = Float.parseFloat(inputWeightString);
        }

        milesString = (seekBar.getProgress());

        // get spinner
        int feet = spinner1.getSelectedItemPosition();
        int inches = spinner2.getSelectedItemPosition();

        //calculation
        double caloriesBurned  = 0.75 * weight * milesString;
        double bmi = (weight * 703) / ((12 * feet + inches) * (12 * feet + inches));

        int finalBurn = (int) caloriesBurned;
        int finalBMI = (int) bmi;

        // display the  results
        burnResultText.setText(finalBurn);
        bmiResult.setText(finalBMI);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }
    @Override
    public void onClick(View views) {
        calculateAndDisplay();
    }
}
