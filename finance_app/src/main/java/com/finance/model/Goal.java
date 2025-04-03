package com.finance.model;

public class Goal {
    private String name;
    private double spendingLimit;
    private int duration;

    public Goal(String name, double spendingLimit, int duration) {
        this.name = name;
        this.spendingLimit = spendingLimit;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public double getSpendingLimit() {
        return spendingLimit;
    }

    public int getDuration() {
        return duration;
    }
}