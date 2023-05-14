package ui;

import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * Class containing the main method and the main logic of the program.
 */
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
/**
 * Displays the main menu of the program and executes the corresponding actions according to the option selected by the user.
 */
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
                    library.generateBook();
                    break;
                case 4:
                    library.generateMagazine();
                    break;
                case 5:
                    library.generateRegularUser();
                    break;
                case 6:
                    library.generatePremiumUser();
                    break;
                case 7:
                    System.out.println("user identifier");
                    String userIdB = reader.nextLine();

                    System.out.println("book identifier");
                    String bookIdB = reader.nextLine();

                    library.buyBook(bookIdB, userIdB);
                    break;
                case 8:
                    System.out.println("user identifier");
                    String userIdM = reader.nextLine();

                    System.out.println("magazine identifier");
                    String magazineIdM = reader.nextLine();

                    library.subscribeMagazine(magazineIdM, userIdM);
                    break;
                case 0:
                    System.out.println("Thank you for using the system, have a nice day");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;

            }

        }

    }
/**
 * Displays the menu for registering, modifying or deleting a bibliographic product and executes the corresponding actions according to the option selected by the user.
 * @throws ParseException if there is an error parsing the publication date of the bibliographic product.
 */
    public void registerModifyDeleteBibliographicProduct() throws ParseException {
        int mainOption;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        do{
            System.out.println("\n1. create book");
            System.out.println("2. modify book");
            System.out.println("3. create magazine");
            System.out.println("4. modify magazine");
            System.out.println("5. delete bibliographic product");

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
 * Requests the user the necessary data to register a bibliographic product and returns them in an array of Strings.
 * @return String array with the data entered by the user in the following order: Identifier, Name, Number of pages, Date of publication.
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
 * Displays the menu for modifying an existing book and executes the corresponding actions according to the option selected by the user.
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
                System.out.println("\nincorrect id");
            }
        } while(stopFlag);

        do{
            System.out.println("\nWhat do you want to modify?");
            System.out.println("1. id");
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
                    System.out.println("New publication date (format(dd/MM/yyyy))");
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
                    System.out.println("Nuevo genre");
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
 * Displays the menu for modifying an existing magazine and executes the corresponding actions according to the option selected by the user.
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
                System.out.println("\nincorrect id");
            }
        } while(stopFlag);

        do{
            System.out.println("\nWhat do you want to modify?");
            System.out.println("1. id");
            System.out.println("2. Name");
            System.out.println("3. Number of pages");
            System.out.println("4. Date of publication");
            System.out.println("5. Category");
            System.out.println("6. Subscription value");
            System.out.println("7. Periodicity of Issuance");

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
                    System.out.println("New publication date (format(dd/MM/yyyy))");
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
                    System.out.println("New subscription value");
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
This class represents a reading session for a particular user.
It contains information about the book he/she is reading, his/her progress in the book and other details that may be relevant.
*/
    public void readingSession(String bibliographicProductId, String userId){
        BibliographicProduct bibliographicProduct = library.findBibliographicProduct(bibliographicProductId);
        User user = library.findUser(userId);

        String option = "";
        int page = 1;

        do{
            System.out.println("Reading session in progress");
            System.out.println("\nReading: " + bibliographicProduct.getName());
            System.out.println("\nReading page " + page + " of " + bibliographicProduct.getTotalPages());
            System.out.println("\nType A to go to the previous page");
            System.out.println("\nType S to go to the next page");
            System.out.println("\nEnter B to return to the Library");
            option = reader.nextLine();

            if (option.equalsIgnoreCase("A")){
                if (page > 1){
                    page--;
                }
            } else if (option.equalsIgnoreCase("S")){
                page++;
                bibliographicProduct.increasePagesRead();
            }
        } while(!option.equalsIgnoreCase("B"));
    }
}
