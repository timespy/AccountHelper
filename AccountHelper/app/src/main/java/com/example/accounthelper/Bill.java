package com.example.accounthelper;

public class Bill {
    private int id;
    private String type;
    private String descrip;
    private double price;
    private int year;
    private int month;
    private int day;

    Bill(){}
    Bill(String type, String descrip, double price, int year, int month, int day){
        this.type = type;
        this.descrip = descrip;
        this.price = price;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    void setId(int id){this.id = id;}
    void setType(String selected){type = selected;}
    void setDescrip(String input){descrip = input;}
    void setPrice(double price){this.price = price;}
    public void setYear(int y){year = y;}
    public void setMonth(int m){month = m;}
    public void setDay(int d){day = d;}

    int getId(){return id;}
    String getType(){return type;}
    String getDescrip(){ return descrip;}
    double getPrice(){return price;}
    int getYear(){return year;}
    int getMonth(){return month;}
    int getDay(){return day;}
}
