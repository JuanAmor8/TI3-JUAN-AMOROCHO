package model;

import java.util.Date;

public class Bill {

    private Date date;
    private int amount;
    private BibliographicProduct bibliographicProduct;

    public Bill(Date date, int amount, BibliographicProduct bibliographicProduct) {
        this.date = date;
        this.amount = amount;
        this.bibliographicProduct = bibliographicProduct;
    }

    public BibliographicProduct getBibliographicProduct() {
        return bibliographicProduct;
    }

    public int getAmount() {
        return amount;
    }
}
