package com.example.phuketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class   MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    private BudgetItemAdapter budgetItemAdapter;
    private RecyclerView budgetItemRecyclerView;
    private List<BudgetItem> budgetItemList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    private FloatingActionButton floatingActionButton;


    @Override
    protected void onStart() {
        super.onStart();

        budgetItemRecyclerView = findViewById(R.id.budget_recyclerView);
        db.collection("budget")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                budgetItemList.clear();

//                        progressBar.setVisibility(View.GONE);
                List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                Log.d(TAG, "onEvent: " + snapshots.size());
                for (int i = 0; i < snapshots.size(); i++) {
                    if (snapshots.get(i).getData().containsKey("name")) {
                        BudgetItem budgetItem = snapshots.get(i).toObject(BudgetItem.class);
                        budgetItemList.add(budgetItem);
                    }
                }

                BudgetItemAdapter budgetItemAdapter = new BudgetItemAdapter( budgetItemList, MainActivity.this);
                budgetItemRecyclerView.setAdapter(budgetItemAdapter);
                budgetItemRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        floatingActionButton = findViewById(R.id.budget_fab);


        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: fab clicked");
                Intent intent = new Intent(MainActivity.this,BudgetFormActivity.class);
                startActivity(intent);
            }
        });
    }

}
