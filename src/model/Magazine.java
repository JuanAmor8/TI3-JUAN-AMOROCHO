package model;

import java.util.Date;

public class Magazine extends BibliographicProduct{

    public enum Category{
        VARIETIES, DESIGN, SCIENTIFIC
    }

    private Category category;
    private int subscriptionValue;
    private String emissionPeriodicity;
    private int activeSubscriptions;

    public Magazine(String id, String name, int totalPages, Date publishingDate, Category category, int subscriptionValue, String emissionPeriodicity) {
        super(id, name, totalPages, publishingDate);
        this.category = category;
        this.subscriptionValue = subscriptionValue;
        this.emissionPeriodicity = emissionPeriodicity;
        activeSubscriptions = 0;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubscriptionValue(int subscriptionValue) {
        this.subscriptionValue = subscriptionValue;
    }

    public void setEmissionPeriodicity(String emissionPeriodicity) {
        this.emissionPeriodicity = emissionPeriodicity;
    }

    public void setActiveSubscriptions(int activeSubscriptions) {
        this.activeSubscriptions = activeSubscriptions;
    }

    public void increaseActiveSubscriptions(){
        this.activeSubscriptions++;
    }

    public int getSubscriptionValue() {
        return subscriptionValue;
    }

    public Category getCategory() {
        return category;
    }
}
