package model;

import java.util.Date;

public class Book extends BibliographicProduct{

    public enum Genre{
        SCIENCE_FICTION, FANCY, HISTORICAL_NOVEL
    }

    private String review;
    private Genre genre;
    private int saleValue;
    private int copiesSold;


    public Book(String id, String name, int totalPages, Date publishingDate, String review, Genre genre, int saleValue) {
        super(id, name, totalPages, publishingDate);
        this.review = review;
        this.genre = genre;
        this.saleValue = saleValue;
        this.copiesSold = 0;

    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public void increaseCopiesSold(){
        this.copiesSold++;
    }

    public int getSaleValue() {
        return saleValue;
    }

    public Genre getGenre() {
        return genre;
    }
}
