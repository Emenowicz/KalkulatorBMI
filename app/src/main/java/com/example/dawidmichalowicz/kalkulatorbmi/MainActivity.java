package com.example.dawidmichalowicz.kalkulatorbmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        float resultPref = preferences.getFloat("BMI", 0);
        if (resultPref != 0) {
            resultTV.setText(new DecimalFormat("0.00").format(resultPref));
        }

        else if (savedInstanceState != null) {
            String savedResult = savedInstanceState.getString("result");
            resultTV.setText(savedResult);
        }

        ArrayAdapter<CharSequence> hAdapter = ArrayAdapter.createFromResource(this, R.array.heightUnits, android.R.layout.simple_spinner_item);
        hAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hUnitSpinner.setAdapter(hAdapter);

        ArrayAdapter<CharSequence> wAdapter = ArrayAdapter.createFromResource(this, R.array.weightUnits, android.R.layout.simple_spinner_item);
        wAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wUnitSpinner.setAdapter(wAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.authorButton:
                intent = new Intent(this, AuthorInfo.class);
                startActivity(intent);
                break;
            case R.id.shareButton:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.messageHead) + String.valueOf(new DecimalFormat("0.00").format(result)) + getString(R.string.messageTail));
                intent.setType("text/plain");
                startActivity(intent);
                break;
            case R.id.saveButton:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("BMI", result);
                editor.apply();
                Toast.makeText(getApplicationContext(), getText(R.string.saveMessage), Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    @OnItemSelected({R.id.hUnitSpinner, R.id.wUnitSpinner})
    void onItemSelected(int position) {
        select = position;
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
        try {
            loadDataFromETs();
            if (select == 1) {
                convertUnits();
            }
            result = counter.countBMI(weight, height);
            resultTV.setText(new DecimalFormat("0.00").format(result));

        } catch (Exception e) {
            resultTV.setText("");
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Utils.hideSoftKeyboard(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", resultTV.getText().toString());
    }

    private void loadDataFromETs() {
        try {
            weight = Float.parseFloat(weightET.getText().toString());
            height = Float.parseFloat(heightET.getText().toString());
        } catch (Exception e) {
            throw new IllegalArgumentException("Brak wartości!");
        }
    }

    private void convertUnits() {
        weight = weight * 0.45f;
        height = height * 30.48f;
    }


}
