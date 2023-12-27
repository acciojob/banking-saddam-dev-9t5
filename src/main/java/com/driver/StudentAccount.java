package com.driver;

public class StudentAccount extends BankAccount{

    String  institutionName;
    private static final double studentMinBalance = 0.0;

    public StudentAccount(String name, double balance, String  institutionName) {
        //minimum balance is 0 by default
        super(name, balance, studentMinBalance);
        this.institutionName = institutionName;
    }

}
