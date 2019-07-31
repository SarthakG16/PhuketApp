package com.example.phuketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ConverterActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 1;

    private EditText sgd_amount, thb_amount;
    private boolean sgdFocus, thbFocus;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;

            switch (item.getItemId()) {
                case R.id.navigation_budget:
                    intent = new Intent(ConverterActivity.this,MainActivity.class);
                    break;
                case R.id.navigation_converter:
                    return false;
                case R.id.navigation_checklist:
                    intent = new Intent(ConverterActivity.this,ChecklistActivity.class);
                    break;

            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            return false;
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        sgd_amount = findViewById(R.id.converter_sgd);
        thb_amount = findViewById(R.id.converter_thb);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        sgd_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (sgdFocus) {
                    if (s.length() == 0) {
                        thb_amount.setText("");
                        return;
                    }
                    double sgd = Double.parseDouble(sgd_amount.getText().toString());
                    double thb = sgd * 22.508;

                    thb_amount.setText(Double.toString(thb));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //0.04442864759

        thb_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (thbFocus) {
                    if (s.length() == 0) {
                        sgd_amount.setText("");
                        return;
                    }
                    double thb = Double.parseDouble(thb_amount.getText().toString());
                    double sgd = thb * 0.04442864759;

                    sgd_amount.setText(Double.toString(sgd));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sgd_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sgdFocus = hasFocus;
            }
        });

        thb_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                thbFocus = hasFocus;
            }
        });
    }

}
