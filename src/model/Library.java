package model;

import java.util.Date;
import java.util.Random;

public class Library {

    private User[] users;
    private BibliographicProduct[] bibliographicProducts;
    private int usersNumber;
    private int bibliographicProductNumber;

    public Library() {
        usersNumber = 0;
        bibliographicProductNumber = 0;
        this.users = new User[1000];
        this.bibliographicProducts = new BibliographicProduct[1000];
    }

    /**
     * This method register a premium or regular user
     * @param name This is the user's name, and it should be a string
     * @param id This is the user's identifier, and should be a string
     * @param premium This is true if the user is premium or false if the user is regular, it should be a boolean
     */
    public void registerUser(String name, String id, boolean premium){
        if(premium){
            users[usersNumber] = new PremiumUser(name, id, new Date(), true);
        } else {
            users[usersNumber] = new RegularUser(name, id, new Date(), false);
        }
        usersNumber++;
    }

    public void registerBook(String id, String name, int totalPages, Date publishingDate, String review, Book.Genre genre, int saleValue){
        Book book = new Book(id, name, totalPages, publishingDate, review, genre, saleValue);
        bibliographicProducts[bibliographicProductNumber] = book;
        bibliographicProductNumber++;
    }

    public void registerMagazine(String id, String name, int totalPages, Date publishingDate, Magazine.Category category, int subscriptionValue, String emissionPeriodicity){
        Magazine magazine = new Magazine(id, name, totalPages, publishingDate, category, subscriptionValue, emissionPeriodicity);
        bibliographicProducts[bibliographicProductNumber] = magazine;
        bibliographicProductNumber++;

    }

    public void deleteBibliographicProduct(String id){
        boolean flag = false;

        for (int i = 0; i < bibliographicProductNumber; i++) {
            if(bibliographicProducts[i].getId().equalsIgnoreCase(id)){
                bibliographicProducts[i] = bibliographicProducts[i+1];
                bibliographicProductNumber--;
                flag = true;
            } else if(flag){
                bibliographicProducts[i] = bibliographicProducts[i+1];
            }
        }
    }

    public BibliographicProduct findBibliographicProduct(String id){
        BibliographicProduct bibliographicProductR = null;

        for (int i = 0; i < bibliographicProductNumber; i++) {
            if(bibliographicProducts[i].getId().equalsIgnoreCase(id)){
                bibliographicProductR = bibliographicProducts[i];
                break;
            }
        }

        return bibliographicProductR;
    }

    public User findUser(String id){
        User user = null;

        for (int i = 0; i < usersNumber; i++) {
            if(users[i].getId().equalsIgnoreCase(id)){
                user = users[i];
                break;
            }
        }

        return user;
    }

    public void buyBook(String bookId, String userId){
        User user = findUser(userId);
        Book book = (Book) findBibliographicProduct(bookId);

        book.increaseCopiesSold();
        user.increaseBibliographicProductsNumber();

        int page = user.getBibliographicProductsNumber() / 25;
        int column = user.getBibliographicProductsNumber() % 5;
        int row = (user.getBibliographicProductsNumber() % 25) / 5;

        user.setPersonalLibrary(book, page, column, row);
        user.setBills(new Bill(new Date(), book.getSaleValue()), page, column, row);
    }

    public void subscribeMagazine(String magazineId, String userId){
        User user = findUser(userId);
        Magazine magazine = (Magazine) findBibliographicProduct(magazineId);

        magazine.increaseActiveSubscriptions();
        user.increaseBibliographicProductsNumber();

        int page = user.getBibliographicProductsNumber() / 25;
        int column = user.getBibliographicProductsNumber() % 5;
        int row = (user.getBibliographicProductsNumber() % 25) / 5;

        user.setPersonalLibrary(magazine, page, column, row);
        user.setBills(new Bill(new Date(), magazine.getSubscriptionValue()), page, column, row);
    }

    public void readingSession(){

    }

    public void generateBook(){
        Book book = new Book(generateRandomString(3), "book", 100, new Date(), "This is a great book", Book.Genre.FANCY, generateRandomValue());
        bibliographicProducts[bibliographicProductNumber] = book;
        bibliographicProductNumber++;
    }

    public void generateMagazine(){
        Magazine magazine = new Magazine(generateRandomString(3), "magazine", 100, new Date(), Magazine.Category.DESIGN, generateRandomValue(), "Every week");
        bibliographicProducts[bibliographicProductNumber] = magazine;
        bibliographicProductNumber++;
    }

    public void generatePremiumUser(){
        users[usersNumber] = new PremiumUser("PremiumUser", generateRandomString(7), new Date(), true);
    }

    public void generateRegularUser(){
        users[usersNumber] = new RegularUser("RegularUser", generateRandomString(7), new Date(), false);
    }

    private String generateRandomString(int count){
        String s = "";
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            s = s + String.valueOf((char)(r.nextInt(26) + 'A'));
        }

        return s;
    }

    private int generateRandomValue(){
        return (int)(Math.random() * 90000 + 10000);
    }
}
