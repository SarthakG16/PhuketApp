package com.example.phuketapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class BudgetFormActivity extends AppCompatActivity {

    private static final String TAG = "BudgetFormActivity";

    private TextInputLayout name, sgd, thb, date, paid;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


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
        hashMap.put("sgd", sgd.getEditText().getText().toString());
        hashMap.put("thb", thb.getEditText().getText().toString());
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
