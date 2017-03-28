package com.example.dawidmichalowicz.kalkulatorbmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hUnitSpinner)
    Spinner hUnitSpinner;
    @BindView(R.id.wUnitSpinner)
    Spinner wUnitSpinner;
    @BindView(R.id.heightET)
    EditText heightET;
    @BindView(R.id.weightET)
    EditText weightET;
    @BindView(R.id.countButton)
    Button countButton;
    @BindView(R.id.resultTVnum)
    TextView resultTV;

    private float weight;
    private float height;
    private int select;
    private BMICounter counter = new BMICounter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> hAdapter = ArrayAdapter.createFromResource(this, R.array.heightUnits, android.R.layout.simple_spinner_item);
        hAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hUnitSpinner.setAdapter(hAdapter);

        ArrayAdapter<CharSequence> wAdapter = ArrayAdapter.createFromResource(this, R.array.weightUnits, android.R.layout.simple_spinner_item);
        wAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wUnitSpinner.setAdapter(wAdapter);
    }

    @OnItemSelected({R.id.hUnitSpinner, R.id.wUnitSpinner})
    void onItemSelected(int position) {
        select=position;
        if (position == 0) {
            wUnitSpinner.setSelection(position);
            hUnitSpinner.setSelection(position);
        } else if (position == 1) {
            wUnitSpinner.setSelection(position);
            hUnitSpinner.setSelection(position);
        }
        Utils.hideSoftKeyboard(this);
    }


    @OnClick(R.id.countButton)
    void countAndDisplayResult() {
        float result;
        try {
            loadDataFromETs();
            if (select == 1) {
                convertUnits();
            }
                result = counter.countBMI(weight, height);
                resultTV.setText(new DecimalFormat("0.00").format(result));
        } catch (Exception e){
//            resultTV.setText("");
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Utils.hideSoftKeyboard(this);
    }

    private void loadDataFromETs() {
        try {
            weight = Float.parseFloat(weightET.getText().toString());
            height = Float.parseFloat(heightET.getText().toString());
        } catch (Exception e){
            throw new IllegalArgumentException("Brak wartości!");
        }
    }

    private void convertUnits() {
        weight = weight * 0.45f;
        height = height * 30.48f;
    }

}
