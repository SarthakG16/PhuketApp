package com.example.phuketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class   MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;

            switch (item.getItemId()) {
                case R.id.navigation_budget:
                    return false;
                case R.id.navigation_converter:
                    intent = new Intent(MainActivity.this,ConverterActivity.class);
                    break;
                case R.id.navigation_checklist:
                    intent = new Intent(MainActivity.this,ChecklistActivity.class);
                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            Log.d(TAG, "onNavigationItemSelected: "+ item.getItemId());
            startActivity(intent);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
