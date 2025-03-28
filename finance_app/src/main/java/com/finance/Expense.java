package org.example;

import java.time.LocalDate;

public class Expense {
    private String name;
    private int amount;
    private String category;
    private LocalDate date;

    public Expense(String name, int amount, String category, LocalDate date) {
        this.name= name;
        this.amount=amount;
        this.category=category;
        this.date=date;
    }
    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public String getCategory(){
        return category;
    }

    public LocalDate getDate(){
        return date;
    }
}
