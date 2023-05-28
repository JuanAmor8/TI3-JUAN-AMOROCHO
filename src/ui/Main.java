package ui;

import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public Scanner reader;
    private Library library;

    public static void main(String[] args) {
        Main main = new Main();
        main.showMainMenu();
    }

    public Main() {
        reader = new Scanner(System.in);
        library = new Library();
    }

    public void showMainMenu() {

        boolean stopFlag = false;

        while (!stopFlag) {

            System.out.println("\nDigite una opcion");
            System.out.println("1. Register a user");
            System.out.println("2. Registering, modifying or deleting a bibliographic product");
            System.out.println("3. Generate book");
            System.out.println("4. Generate magazine");
            System.out.println("5. Generate regular user");
            System.out.println("6. Generate premium user");
            System.out.println("7. Buy book");
            System.out.println("8. Subscribe to a magazine");
            System.out.println("9. Display a user's library");
            System.out.println("10. Pages read for each bibliographic product");
            System.out.println("11. Most read genre and category");
            System.out.println("12. Top 5 books and top 5 magazines");
            System.out.println("13. Total number of books sold");
            System.out.println("14. Total number of active subscriptions");
            System.out.println("0. Exit");

            int mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("Name");
                    String name = reader.nextLine();

                    System.out.println("Id");
                    String id = reader.nextLine();

                    System.out.println("Type premium if the user is a premium category user or regular if the user is a regular category user.");
                    String premium = reader.nextLine();

                    boolean togglePremium;
                    if(premium.equalsIgnoreCase("premium")){
                        togglePremium = true;
                    } else {
                        togglePremium = false;
                    }

                    library.registerUser(name, id, togglePremium);
                    break;
                case 2:
                    try{
                        registerModifyDeleteBibliographicProduct();
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Id of the book: " + library.generateBook());
                    break;
                case 4:
                    System.out.println("Id of the magazine: " + library.generateMagazine());
                    break;
                case 5:
                    System.out.println("User id: " + library.generateRegularUser());
                    break;
                case 6:
                    System.out.println("User id: " + library.generatePremiumUser());
                    break;
                case 7:
                    System.out.println("User identifier");
                    String userIdB = reader.nextLine();

                    System.out.println("Book identifier");
                    String bookIdB = reader.nextLine();

                    library.buyBook(bookIdB, userIdB);
                    break;
                case 8:
                    System.out.println("User identifier");
                    String userIdM = reader.nextLine();

                    System.out.println("Magazine identifier");
                    String magazineIdM = reader.nextLine();

                    library.subscribeMagazine(magazineIdM, userIdM);
                    break;
                case 9:
                    showLibrary();
                    break;
                case 10:
                    System.out.println(library.pagesReadForEachBibliographicProduct());
                    break;
                case 11:
                    System.out.println(library.topBibliographicProductsByGenreOrCategory());
                    break;
                case 12:
                    System.out.println(library.topFiveBooksAndMagazines());
                    break;
                case 13:
                    System.out.println(library.booksSold());
                    break;
                case 14:
                    System.out.println(library.magazineActiveSubscriptions());
                    break;
                case 0:
                    System.out.println("Thank you for using the system");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;

            }

        }

    }

    /**
     * THis method launch a method to manage a bibliographic product
     * @throws ParseException
     */
    public void registerModifyDeleteBibliographicProduct() throws ParseException {
        int mainOption;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        do{
            System.out.println("\n1. Create book");
            System.out.println("2. Modify book");
            System.out.println("3. Create magazine");
            System.out.println("4. Modify magazine");
            System.out.println("5. Delete bibliographic product");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    String [] infoB = infoBibliographicProduct();

                    System.out.println("Review");
                    String review = reader.nextLine();

                    System.out.println("Genre");
                    String genre = reader.nextLine();

                    System.out.println("Name");
                    int saleValue = reader.nextInt();
                    reader.nextLine();

                    Date publishingDateB = format.parse(infoB[3]);

                    library.registerBook(infoB[0], infoB[1], Integer.parseInt(infoB[2]), publishingDateB, review, Book.Genre.valueOf(genre), saleValue);
                    break;
                case 2:
                    modifyBook();
                    break;
                case 3:
                    String [] infoM = infoBibliographicProduct();

                    System.out.println("Review");
                    String category = reader.nextLine();

                    System.out.println("Genre");
                    int subscriptionValue = reader.nextInt();
                    reader.nextLine();

                    System.out.println("Name");
                    String emissionPeriodicity = reader.nextLine();

                    Date publishingDateM = format.parse(infoM[3]);

                    library.registerMagazine(infoM[0], infoM[1], Integer.parseInt(infoM[2]), publishingDateM, Magazine.Category.valueOf(category), subscriptionValue, emissionPeriodicity);

                    break;
                case 4:
                    modifyMagazine();
                    break;
                case 5:
                    System.out.println("bibliographic product identifier");
                    String bpId = reader.nextLine();
                    library.deleteBibliographicProduct(bpId);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;

            }

        } while (mainOption < 1 || mainOption > 5);
    }

    /**
     * This method ask the user for bibliographic product info
     * @return
     */
    public String [] infoBibliographicProduct(){
        String [] info = new String[4];

        System.out.println("Id");
        info[0] = reader.nextLine();

        System.out.println("Name");
        info[1] = reader.nextLine();

        System.out.println("Number of pages");
        info[2] = reader.nextLine();

        System.out.println("Date of publication (format(dd/MM/yyyy))");
        info[3] = reader.nextLine();

        return info;
    }

    /**
     * This method launch a menu for modifying a book
     */
    public void modifyBook(){
        int mainOption;
        boolean stopFlag = true;
        Book book;

        do{
            System.out.println("Inserts the book identifier");
            String bookId = reader.nextLine();
            book = (Book) library.findBibliographicProduct(bookId);
            if(book != null){
                stopFlag = false;
            } else {
                System.out.println("\nIncorrect Id");
            }
        } while(stopFlag);

        do{
            System.out.println("\n¿What u want to modify?");
            System.out.println("1. Id");
            System.out.println("2. Name");
            System.out.println("3. Number of pages");
            System.out.println("4. Date of publication");
            System.out.println("5. Review");
            System.out.println("6. Genre");
            System.out.println("7. Sales value");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("New id");
                    String id = reader.nextLine();
                    book.setId(id);
                    break;
                case 2:
                    System.out.println("New name");
                    String name = reader.nextLine();
                    book.setName(name);
                    break;
                case 3:
                    System.out.println("New number of pages");
                    int totalPages = reader.nextInt();
                    reader.nextLine();
                    book.setTotalPages(totalPages);
                    break;
                case 4:
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("New date of publication (format(dd/MM/yyyy))");
                    String publishingDate = reader.nextLine();
                    try {
                        Date date = format.parse(publishingDate);
                        book.setPublishingDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("New review");
                    String review = reader.nextLine();
                    book.setReview(review);
                    break;
                case 6:
                    System.out.println("New genre");
                    String genre = reader.nextLine();
                    book.setGenre(Book.Genre.valueOf(genre));
                    break;
                case 7:
                    System.out.println("New sales value");
                    int saleValue = reader.nextInt();
                    reader.nextLine();
                    book.setSaleValue(saleValue);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;

            }

        } while (mainOption < 1 || mainOption > 7);
    }

    /**
     * THis method launch a menu for modifying a magazine
     */
    public void modifyMagazine(){
        int mainOption;
        boolean stopFlag = true;
        Magazine magazine;

        do{
            System.out.println("Insert the magazine identifier");
            String bookId = reader.nextLine();
            magazine = (Magazine) library.findBibliographicProduct(bookId);
            if(magazine != null){
                stopFlag = false;
            } else {
                System.out.println("\nIncorrect id");
            }
        } while(stopFlag);

        do{
            System.out.println("\n¿What u want to modify?");
            System.out.println("1. Id");
            System.out.println("2. Name");
            System.out.println("3. Number of pages");
            System.out.println("4. Date of publication");
            System.out.println("5. Category");
            System.out.println("6. Suscription value");
            System.out.println("7. Issuance Periodicity");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("New id");
                    String id = reader.nextLine();
                    magazine.setId(id);
                    break;
                case 2:
                    System.out.println("New name");
                    String name = reader.nextLine();
                    magazine.setName(name);
                    break;
                case 3:
                    System.out.println("New number of pages");
                    int totalPages = reader.nextInt();
                    reader.nextLine();
                    magazine.setTotalPages(totalPages);
                    break;
                case 4:
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("New date of publication (format(dd/MM/yyyy))");
                    String publishingDate = reader.nextLine();
                    try {
                        Date date = format.parse(publishingDate);
                        magazine.setPublishingDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("New category");
                    String category = reader.nextLine();
                    magazine.setCategory(Magazine.Category.valueOf(category));
                    break;
                case 6:
                    System.out.println("New value of suscription");
                    int subscriptionValue = reader.nextInt();
                    reader.nextLine();
                    magazine.setSubscriptionValue(subscriptionValue);
                    break;
                case 7:
                    System.out.println("New broadcasting periodicity");
                    String emissionPeriodicity = reader.nextLine();
                    magazine.setEmissionPeriodicity(emissionPeriodicity);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;

            }

        } while (mainOption < 1 || mainOption > 7);
    }

    /**
     * This method launch an interface to simulate a reading session of a user
     * @param bibliographicProductId This is the bibliographic product read
     * @param userId This is the user reading
     */
    public void readingSession(String bibliographicProductId, String userId){
        BibliographicProduct bibliographicProduct = library.findBibliographicProduct(bibliographicProductId);
        User user = library.findUser(userId);

        String option = "";
        int page = 1;

        do{
            System.out.println("Reading session in progress");
            System.out.println("\nReading: " + bibliographicProduct.getName());
            System.out.println("\nReading page " + page + " de " + bibliographicProduct.getTotalPages());
            System.out.println("\nType P to go to the previous page");
            System.out.println("\nType N to go to the next page");
            System.out.println("\nType B to return to the Library");
            option = reader.nextLine();

            if (option.equalsIgnoreCase("P")){
                if (page > 1){
                    page--;
                }
            } else if (option.equalsIgnoreCase("N")){
                page++;
                bibliographicProduct.increasePagesRead();
            }
        } while(!option.equalsIgnoreCase("B"));
    }

    /**
     * This method launch an interface that simulate a library
     */
    public void showLibrary(){
        User user = null;
        String toggle = "";
        int a = 0;

        do{
            System.out.println("User id");
            String id = reader.nextLine();

            user = library.findUser(id);
        } while(user == null);

        do{
            System.out.println("     0  |  1  |  2  |  3  |  4  ");
            for (int i = 0; i < 5; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 5; j++) {
                    if(user.getPersonalLibrary()[a][i][j] != null){
                        System.out.print("| " + user.getPersonalLibrary()[a][i][j].getId() + " ");
                    } else {
                        System.out.print("| ___ ");
                    }
                }
                System.out.println("");
            }

            System.out.println("Type in the x,y coordinate or the corresponding code");
            System.out.println("of the bibliographic product to start a reading session");
            System.out.println("\nType P to go to the previous page");
            System.out.println("\nType N to go to the next page");
            System.out.println("\nEnter E to exit");

            toggle = reader.nextLine();

            if(!toggle.equalsIgnoreCase("P") && !toggle.equalsIgnoreCase("N") && !toggle.equalsIgnoreCase("E")){
                if(toggle.matches("[A-Z].*")){
                    readingSession(library.findBibliographicProduct(toggle).getId(), user.getId());
                } else {
                    String [] positions = toggle.split(",");
                    if(user.getPersonalLibrary()[a][Integer.parseInt(positions[1])][Integer.parseInt(positions[0])] != null){
                        readingSession(user.getPersonalLibrary()[a][Integer.parseInt(positions[1])][Integer.parseInt(positions[0])].getId(), user.getId());
                    } else {
                        System.out.println("\nThis shelf is empty\n");
                    }
                }
            } else if(toggle.equalsIgnoreCase("P")){
                if(a > 0){
                    a--;
                }
            } else if(toggle.equalsIgnoreCase("N")){
                a++;
            }
        } while(!toggle.equalsIgnoreCase("E"));
    }
}
