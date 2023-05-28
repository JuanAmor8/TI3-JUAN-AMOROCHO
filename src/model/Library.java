package model;

import java.util.Date;
import java.util.Random;

public class Library {

    private User[] users;
    private BibliographicProduct[] bibliographicProducts;
    private int usersNumber;
    private int bibliographicProductNumber;
    private int billsNumber;
    private Bill [] bills;

    public Library() {
        this.usersNumber = 0;
        this.bibliographicProductNumber = 0;
        this.billsNumber = 0;
        this.users = new User[1000];
        this.bibliographicProducts = new BibliographicProduct[1000];
        this.bills = new Bill[1000];
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

    /**
     * This method takes the book data, creates the object, and it adds to the array that contains the bibliographic products
     * @param id This is the book's id
     * @param name This is the book's name
     * @param totalPages This is the book's total pages
     * @param publishingDate This is the book's publishing date
     * @param review This is the book's review
     * @param genre This is the book's genre
     * @param saleValue This is the book sale value
     */
    public void registerBook(String id, String name, int totalPages, Date publishingDate, String review, Book.Genre genre, int saleValue){
        Book book = new Book(id, name, totalPages, publishingDate, review, genre, saleValue);
        bibliographicProducts[bibliographicProductNumber] = book;
        bibliographicProductNumber++;
    }

    /**
     * This method takes the magazine data, creates the object, and it adds to the array that contains the bibliographic products
     * @param id This is the magazine's id
     * @param name This is the magazine's name
     * @param totalPages This is the magazine's total pages
     * @param publishingDate This is the magazine's publishing date
     * @param category This is the magazine's category
     * @param subscriptionValue This is the magazine subscription value
     * @param emissionPeriodicity This is the magazine emission periodicity
     */
    public void registerMagazine(String id, String name, int totalPages, Date publishingDate, Magazine.Category category, int subscriptionValue, String emissionPeriodicity){
        Magazine magazine = new Magazine(id, name, totalPages, publishingDate, category, subscriptionValue, emissionPeriodicity);
        bibliographicProducts[bibliographicProductNumber] = magazine;
        bibliographicProductNumber++;

    }

    /**
     * This method receive the id bibliographic product and delete the object
     * @param id This is the bibliographic product id
     */
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

    /**
     * This method receive the bibliographic product id and return its object
     * @param id This is the bibliographic product id
     * @return This contains the bibliographic product of the object found
     */
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

    /**
     * This method receive the user's id and return its object
     * @param id This is the user's id
     * @return
     */
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

    /**
     * This method receive the book and user's id and add the object of the book to the user's library
     * @param bookId This is the book's id
     * @param userId This is the user's id
     */
    public void buyBook(String bookId, String userId){
        User user = findUser(userId);
        Book book = (Book) findBibliographicProduct(bookId);

        int page = user.getBibliographicProductsNumber() / 25;
        int column = user.getBibliographicProductsNumber() % 5;
        int row = (user.getBibliographicProductsNumber() % 25) / 5;

        book.increaseCopiesSold();
        user.increaseBibliographicProductsNumber();

        user.setPersonalLibrary(book, page, column, row);
        user.setBills(new Bill(new Date(), book.getSaleValue(), book), page, column, row);
        bills[billsNumber] = new Bill(new Date(), book.getSaleValue(), book);
        billsNumber++;
    }

    /**
     * This method receive the magazine and user's id and add the object of the book to the user's library
     * @param magazineId This is the magazine's id
     * @param userId This is the user's id
     */
    public void subscribeMagazine(String magazineId, String userId){
        User user = findUser(userId);
        Magazine magazine = (Magazine) findBibliographicProduct(magazineId);

        int page = user.getBibliographicProductsNumber() / 25;
        int column = user.getBibliographicProductsNumber() % 5;
        int row = (user.getBibliographicProductsNumber() % 25) / 5;

        magazine.increaseActiveSubscriptions();
        user.increaseBibliographicProductsNumber();

        user.setPersonalLibrary(magazine, page, row, column);
        user.setBills(new Bill(new Date(), magazine.getSubscriptionValue(), magazine), page, column, row);
        bills[billsNumber] = new Bill(new Date(), magazine.getSubscriptionValue(), magazine);
        billsNumber++;
    }

    /**
     * This method generates a random book
     */
    public String generateBook(){
        String id = "AB1";

        Book book = new Book(id, "book", 100, new Date(), "This is a great book", Book.Genre.FANCY, generateRandomValue());
        bibliographicProducts[bibliographicProductNumber] = book;
        bibliographicProductNumber++;

        return id;
    }

    /**
     * This method generates a random magazine
     */
    public String generateMagazine(){
        String id = "AM1";

        Magazine magazine = new Magazine(id, "magazine", 100, new Date(), Magazine.Category.DESIGN, generateRandomValue(), "Every week");
        bibliographicProducts[bibliographicProductNumber] = magazine;
        bibliographicProductNumber++;

        return id;
    }

    /**
     * This method a random premium user
     */
    public String generatePremiumUser(){
        String id = "1234";

        users[usersNumber] = new PremiumUser("PremiumUser", id, new Date(), true);
        usersNumber++;

        return id;
    }

    /**
     * This method generates a random regular user
     */
    public String generateRegularUser(){
        String id = "2345";

        users[usersNumber] = new RegularUser("RegularUser", id, new Date(), false);
        usersNumber++;

        return id;
    }

    /**
     * This method generate random number between 10000 and 100000
     * @return THe number generated
     */
    private int generateRandomValue(){
        return (int)(Math.random() * 90000 + 10000);
    }

    /**
     * This method returns the pages read for each bibliographic product
     * @return This contains the data with the pages read for each bibliographic product
     */
    public String pagesReadForEachBibliographicProduct(){
        String data = "";

        for (int i = 0; i < bibliographicProductNumber; i++) {
            if(bibliographicProducts[i] instanceof Book){
                data = data + "Book: id-" + bibliographicProducts[i].getId() + " number of pages read-" + bibliographicProducts[i].getPagesRead() + "\n";
            } else {
                data = data + "Magazine: id-" + bibliographicProducts[i].getId() + " number of pages read-" + bibliographicProducts[i].getPagesRead() + "\n";
            }
        }

        return data;
    }

    /**
     * This method return the book genre most read and the magazine category most read
     * @return This contains the data
     */
    public String topBibliographicProductsByGenreOrCategory(){
        String data;
        int varieties = 0, design = 0, scientific = 0;
        int scienceFiction = 0, fancy = 0, historicalNovel = 0;

        for (int i = 0; i < bibliographicProductNumber; i++) {
            if(bibliographicProducts[i] instanceof Book){
                Book book = (Book) bibliographicProducts[i];

                if(book.getGenre() == Book.Genre.FANCY){
                    fancy += book.getPagesRead();
                } else if(book.getGenre() == Book.Genre.HISTORICAL_NOVEL){
                    historicalNovel += book.getPagesRead();
                } else {
                    scienceFiction += book.getPagesRead();
                }
            } else {
                Magazine magazine = (Magazine) bibliographicProducts[i];

                if(magazine.getCategory() == Magazine.Category.DESIGN){
                    design += magazine.getPagesRead();
                } else if(magazine.getCategory() == Magazine.Category.SCIENTIFIC){
                    scientific += magazine.getPagesRead();
                } else {
                    varieties += magazine.getPagesRead();
                }
            }
        }

        if(scienceFiction > fancy && scienceFiction > historicalNovel){
            data = "The most read genre is: Science Fiction - Number of pages read: " + scienceFiction;
        } else if(fancy > historicalNovel){
            data = "The most read genre is: Fancy - Number of pages read: " + fancy;
        } else {
            data = "The most read genre is: Historical Novel - Number of pages read: " + historicalNovel;
        }

        if(varieties > design && varieties > scientific){
            data = data + "\nThe most read category is: Varieties - Number of pages read: " + varieties;
        } else if(design > scientific){
            data = data + "\nThe most read category is: Design - Number of pages read: " + design;
        } else {
            data = data + "\nThe most read category is: Scientific - Number of pages read: " + scientific;
        }

        return data;
    }

    /**
     * This method returns a string with the top five of most read books and magazines
     * @return This contains the data
     */
    public String topFiveBooksAndMagazines(){
        String data;

        int booksN = 0;
        int magazinesN = 0;

        Book [] books = new Book[1000];
        Magazine [] magazines = new Magazine[1000];

        for (int i = 0; i < bibliographicProductNumber; i++) {
            if(bibliographicProducts[i] instanceof Book){
                books[booksN] = (Book) bibliographicProducts[i];
                booksN++;
            } else {
                magazines[magazinesN] = (Magazine) bibliographicProducts[i];
                magazinesN++;
            }
        }

        sortBibliographicProducts(books, booksN);
        sortBibliographicProducts(magazines, magazinesN);

        data = "Books\n";

        if(books[0] != null){
             data = data + "1. " + books[0].getName() + " - " + books[0].getGenre() + " - " + books[0].getPagesRead() + "\n";
        }

        if(books[1] != null){
            data = data + "2. " + books[1].getName() + " - " + books[1].getGenre() + " - " + books[1].getPagesRead() + "\n";
        }

        if(books[2] != null){
            data = data + "3. " + books[2].getName() + " - " + books[2].getGenre() + " - " + books[2].getPagesRead() + "\n";
        }

        if(books[3] != null){
            data = data + "4. " + books[3].getName() + " - " + books[3].getGenre() + " - " + books[3].getPagesRead() + "\n";
        }

        if(books[4] != null){
            data = data + "5. " + books[4].getName() + " - " + books[4].getGenre() + " - " + books[4].getPagesRead() + "\n";
        }

        data = data + "Magazines\n";

        if(magazines[0] != null){
            data = data + "1. " + magazines[0].getName() + " - " + magazines[0].getCategory() + " - " + magazines[0].getPagesRead() + "\n";
        }

        if(magazines[1] != null){
            data = data + "2. " + magazines[1].getName() + " - " + magazines[1].getCategory() + " - " + magazines[1].getPagesRead() + "\n";
        }

        if(magazines[2] != null){
            data = data + "3. " + magazines[2].getName() + " - " + magazines[2].getCategory() + " - " + magazines[2].getPagesRead() + "\n";
        }

        if(magazines[3] != null){
            data = data + "4. " + magazines[3].getName() + " - " + magazines[3].getCategory() + " - " + magazines[3].getPagesRead() + "\n";
        }

        if(magazines[4] != null){
            data = data + "5. " + magazines[4].getName() + " - " + magazines[4].getCategory() + " - " + magazines[4].getPagesRead() + "\n";
        }

        return data;
    }

    /**
     * This method sort by the number of pages read a array
     * @param bibliographicProductT This is the array to sort
     * @param n This is the array's length
     */
    private void sortBibliographicProducts(BibliographicProduct [] bibliographicProductT, int n){
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (bibliographicProductT[j].getPagesRead() > bibliographicProductT[j + 1].getPagesRead()) {
                    BibliographicProduct temp = bibliographicProductT[j];
                    bibliographicProductT[j] = bibliographicProductT[j + 1];
                    bibliographicProductT[j + 1] = temp;
                }
            }
        }
    }

    /**
     * This method returns the books sold and the value of all sales
     * @return This contains the data
     */
    public String booksSold(){
        String data;

        int booksSold = 0;
        int totalValue = 0;

        for (int i = 0; i < billsNumber; i++) {
            if(bills[i].getBibliographicProduct() instanceof Book){
                booksSold++;
                totalValue += bills[i].getAmount();
            }
        }

        data = "Have been sold " + booksSold + " books" + " and the total sales value is " + totalValue;

        return data;
    }

    /**
     * This method returns the magazine active subscriptions and the value of total subscriptions
     * @return This contains the data
     */
    public String magazineActiveSubscriptions(){
        String data;

        int activeSubscriptions = 0;
        int totalValue = 0;

        for (int i = 0; i < billsNumber; i++) {
            if(bills[i].getBibliographicProduct() instanceof Magazine){
                activeSubscriptions++;
                totalValue += bills[i].getAmount();
            }
        }

        data = "there is " + activeSubscriptions + " active subscriptions " + " and the total sales value is " + totalValue;

        return data;
    }
}
