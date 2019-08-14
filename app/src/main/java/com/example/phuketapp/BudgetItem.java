package com.example.phuketapp;

public class BudgetItem {

    public String name;
    public double sgd;
    public double thb;
    public String date;
    public String paid;

    public BudgetItem(String name, double sgd, double thb, String date, String paid) {
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

    public double getSgd() {
        return sgd;
    }

    public double getThb() {
        return thb;
    }

    public String getDate() {
        return date;
    }

    public String getPaid() {
        return paid;
    }
}
