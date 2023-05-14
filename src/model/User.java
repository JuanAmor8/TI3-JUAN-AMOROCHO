package model;

import java.util.Date;

public class User {

    private String name;
    private String id;
    private Date bindingDate;
    private boolean premium;
    private BibliographicProduct [][][] personalLibrary;
    private Bill [][][] bills;
    private int bibliographicProductsNumber;

    public User(String name, String id, Date bindingDate, boolean premium) {
        this.name = name;
        this.id = id;
        this.bindingDate = bindingDate;
        this.premium = premium;
        personalLibrary = new BibliographicProduct[1000][5][5];
        bills = new Bill[1000][5][5];
        bibliographicProductsNumber = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBindingDate() {
        return bindingDate;
    }

    public void setBindingDate(Date bindingDate) {
        this.bindingDate = bindingDate;
    }

    public void setPersonalLibrary(BibliographicProduct bibliographicProduct, int a, int b, int c) {
        this.personalLibrary[a][b][c] = bibliographicProduct;
    }

    public void setBills(Bill bill, int a, int b, int c) {
        this.bills[a][b][c] = bill;
    }

    public int getBibliographicProductsNumber() {
        return bibliographicProductsNumber;
    }

    public void increaseBibliographicProductsNumber() {
        this.bibliographicProductsNumber++;
    }
}
