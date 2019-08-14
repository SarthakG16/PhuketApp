package com.example.phuketapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class BudgetFormActivity extends AppCompatActivity {

    private static final String TAG = "BudgetFormActivity";

    private TextInputLayout name, sgd, thb, date, paid;

    private boolean sgdFocus, thbFocus;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_form);
        getSupportActionBar().hide();

        name = findViewById(R.id.budgetForm_name);
        sgd = findViewById(R.id.budgetForm_sgd);
        thb = findViewById(R.id.budgetForm_thb);
        date = findViewById(R.id.budgetForm_date);
        paid = findViewById(R.id.budgetForm_paid);




        DocumentReference docRef = db.collection("budget").document("index");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Object temp = snapshot.getData().get("index");
                    index = String.valueOf(temp);
                    int x = Integer.valueOf(index);
                    Log.d(TAG, "onEvent: "+ x);
                    Log.d(TAG, "Current data: " + snapshot.getData().get("index"));

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });


        sgd.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: HIHIHI");
                if (sgdFocus) {
                    Log.d(TAG, "onTextChanged: HIHIHI");
                    if (s.length() == 0) {
                        thb.getEditText().setText("");
                        return;
                    }
                    double sgd_temp = Double.parseDouble(sgd.getEditText().getText().toString());
                    double thb_temp = sgd_temp * 22.508;

                    thb.getEditText().setText(Double.toString(thb_temp));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        thb.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (thbFocus) {
                    if (s.length() == 0) {
                        sgd.getEditText().setText("");
                        return;
                    }
                    double thb_temp = Double.parseDouble(thb.getEditText().getText().toString());
                    double sgd_temp = thb_temp * 0.04442864759;

                    sgd.getEditText().setText(Double.toString(sgd_temp));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sgd.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sgdFocus = hasFocus;
            }
        });

        thb.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                thbFocus = hasFocus;
            }
        });

    }


    private boolean validate(TextInputLayout check) {
        String emailInput = check.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            check.setError("Field is required");
            return false;
        } else {
            check.setError(null);
            return true;
        }
    }

    public void submitButton(View view) {
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name.getEditText().getText().toString());
        hashMap.put("sgd", Double.parseDouble(sgd.getEditText().getText().toString()));
        hashMap.put("thb", Double.parseDouble(thb.getEditText().getText().toString()));
        hashMap.put("date", date.getEditText().getText().toString());
        hashMap.put("paid", paid.getEditText().getText().toString());


        db.collection("budget")
                .add(hashMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BudgetFormActivity.this, "Cannot Add Caregiver", Toast.LENGTH_SHORT).show();
                    }
                });

        //updating index
//        HashMap<String, Object> updatedIndex = new HashMap<>();
//        int  x = Integer.valueOf(index);
//        x++;
//        updatedIndex.put("index",x);
//
//        db.collection("budget").document("index")
//                .set(updatedIndex);

        finish();
    }
}
