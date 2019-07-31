package com.example.phuketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ChecklistActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;

            switch (item.getItemId()) {
                case R.id.navigation_budget:
                    intent = new Intent(ChecklistActivity.this,MainActivity.class);
                    break;
                case R.id.navigation_converter:
                    intent = new Intent(ChecklistActivity.this,ConverterActivity.class);
                    break;
                case R.id.navigation_checklist:
                    return false;
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

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
