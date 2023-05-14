package model;

import java.util.Date;

public class Bill {

    private Date date;
    private int amount;

    public Bill(Date date, int amount) {
        this.date = date;
        this.amount = amount;
    }
}
