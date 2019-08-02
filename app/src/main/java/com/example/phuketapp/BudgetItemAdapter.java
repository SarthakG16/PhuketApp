package com.example.phuketapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class BudgetItemAdapter extends RecyclerView.Adapter<BudgetItemAdapter.BudgetItemHolder> {

    private List<BudgetItem> budgetItemList;
    private Context mContext;

    public class BudgetItemHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView sgd;
        TextView thb;
        TextView date;
        TextView paid;
        RelativeLayout parentLayout;

        public BudgetItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.budget_item_name);
            sgd = itemView.findViewById(R.id.budget_item_sgd);
            thb = itemView.findViewById(R.id.budget_item_thb);
            date = itemView.findViewById(R.id.budget_item_date);
            paid = itemView.findViewById(R.id.budget_item_paid);
            parentLayout = itemView.findViewById(R.id.budget_item_layout);
        }
    }

    public BudgetItemAdapter(List<BudgetItem> budgetItemList, Context mContext) {
        this.budgetItemList = budgetItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BudgetItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.budget_item,viewGroup,false);
        return new BudgetItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetItemHolder budgetItemHolder, int i) {
        BudgetItem budgetItem = budgetItemList.get(i);

        budgetItemHolder.name.setText(budgetItem.name);
        budgetItemHolder.sgd.setText("SGD: " + budgetItem.sgd);
        budgetItemHolder.thb.setText("THB: " + budgetItem.thb);
        budgetItemHolder.date.setText("Date: " + budgetItem.date);
        budgetItemHolder.paid.setText("Paid: " + budgetItem.getPaid());

    }

    @Override
    public int getItemCount() {
        return budgetItemList.size();
    }


}
