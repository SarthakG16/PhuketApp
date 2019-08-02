package com.example.phuketapp;

public class BudgetItem {

    public String name;
    public String sgd;
    public String thb;
    public String date;
    public String paid;

    public BudgetItem(String name, String sgd, String thb, String date, String paid) {
        this.name = name;
        this.sgd = sgd;
        this.thb = thb;
        this.date = date;
        this.paid = paid;
    }

    public BudgetItem() {
    }

    public String getName() {
        return name;
    }

    public String getSgd() {
        return sgd;
    }

    public String getThb() {
        return thb;
    }

    public String getDate() {
        return date;
    }

    public String getPaid() {
        return paid;
    }
}
