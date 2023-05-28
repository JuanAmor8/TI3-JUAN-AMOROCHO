package model;

import java.util.Date;

public class BibliographicProduct {

    private String id;
    private String name;
    private int totalPages;
    private Date publishingDate;
    private int pagesRead;

    public BibliographicProduct(String id, String name, int totalPages, Date publishingDate) {
        this.id = id;
        this.name = name;
        this.totalPages = totalPages;
        this.publishingDate = publishingDate;
        this.pagesRead = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public void increasePagesRead(){
        this.pagesRead++;
    }

    public String getName() {
        return name;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPagesRead() {
        return pagesRead;
    }
}
