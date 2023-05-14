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
            System.out.println("1. Registrar un usuario");
            System.out.println("2. Registrar, modificar o eliminar un producto bibliografico");
            System.out.println("3. Generar libro");
            System.out.println("4. Generar revista");
            System.out.println("5. Generar usuario regular");
            System.out.println("6. Generar usuario premium");
            System.out.println("7. Comprar libro");
            System.out.println("8. Suscribirse a una revista");
            System.out.println("0. Salir");

            int mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("Nombre");
                    String name = reader.nextLine();

                    System.out.println("Cedula");
                    String id = reader.nextLine();

                    System.out.println("Escribe premium si el usuario es de categoria premium o regular si el usuario es de categoria regu;lar");
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
                    System.out.println("identificador del usuario");
                    String userIdB = reader.nextLine();

                    System.out.println("identificador del libro");
                    String bookIdB = reader.nextLine();

                    library.buyBook(bookIdB, userIdB);
                    break;
                case 8:
                    System.out.println("identificador del usuario");
                    String userIdM = reader.nextLine();

                    System.out.println("identificador de la revista");
                    String magazineIdM = reader.nextLine();

                    library.subscribeMagazine(magazineIdM, userIdM);
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("Por favor digite una opcion valida");
                    break;

            }

        }

    }

    public void registerModifyDeleteBibliographicProduct() throws ParseException {
        int mainOption;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        do{
            System.out.println("\n1. crear libro");
            System.out.println("2. modificar libro");
            System.out.println("3. crear revista");
            System.out.println("4. modificar revista");
            System.out.println("5. borrar producto bibliografico");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    String [] infoB = infoBibliographicProduct();

                    System.out.println("Reseña");
                    String review = reader.nextLine();

                    System.out.println("Genero");
                    String genre = reader.nextLine();

                    System.out.println("Nombre");
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

                    System.out.println("Reseña");
                    String category = reader.nextLine();

                    System.out.println("Genero");
                    int subscriptionValue = reader.nextInt();
                    reader.nextLine();

                    System.out.println("Nombre");
                    String emissionPeriodicity = reader.nextLine();

                    Date publishingDateM = format.parse(infoM[3]);

                    library.registerMagazine(infoM[0], infoM[1], Integer.parseInt(infoM[2]), publishingDateM, Magazine.Category.valueOf(category), subscriptionValue, emissionPeriodicity);

                    break;
                case 4:
                    modifyMagazine();
                    break;
                case 5:
                    System.out.println("identificador del producto bibliografico");
                    String bpId = reader.nextLine();
                    library.deleteBibliographicProduct(bpId);
                    break;
                default:
                    System.out.println("Por favor digite una opcion valida");
                    break;

            }

        } while (mainOption < 1 || mainOption > 5);
    }

    public String [] infoBibliographicProduct(){
        String [] info = new String[4];

        System.out.println("Identificador");
        info[0] = reader.nextLine();

        System.out.println("Nombre");
        info[1] = reader.nextLine();

        System.out.println("Numero de paginas");
        info[2] = reader.nextLine();

        System.out.println("Fecha de publicacion (formato(dd/MM/yyyy))");
        info[3] = reader.nextLine();

        return info;
    }

    public void modifyBook(){
        int mainOption;
        boolean stopFlag = true;
        Book book;

        do{
            System.out.println("Inserta el identificador del libro");
            String bookId = reader.nextLine();
            book = (Book) library.findBibliographicProduct(bookId);
            if(book != null){
                stopFlag = false;
            } else {
                System.out.println("\nid incorrecto");
            }
        } while(stopFlag);

        do{
            System.out.println("\n¿Que quieres modificar?");
            System.out.println("1. id");
            System.out.println("2. Nombre");
            System.out.println("3. Numero de paginas");
            System.out.println("4. Fecha de publicacion");
            System.out.println("5. Reseña");
            System.out.println("6. Genero");
            System.out.println("7. Valor de venta");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("Nuevo id");
                    String id = reader.nextLine();
                    book.setId(id);
                    break;
                case 2:
                    System.out.println("Nuevo nombre");
                    String name = reader.nextLine();
                    book.setName(name);
                    break;
                case 3:
                    System.out.println("Nuevo numero de paginas");
                    int totalPages = reader.nextInt();
                    reader.nextLine();
                    book.setTotalPages(totalPages);
                    break;
                case 4:
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Nueva fecha de publicación (formato(dd/MM/yyyy))");
                    String publishingDate = reader.nextLine();
                    try {
                        Date date = format.parse(publishingDate);
                        book.setPublishingDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Nueva reseña");
                    String review = reader.nextLine();
                    book.setReview(review);
                    break;
                case 6:
                    System.out.println("Nuevo genero");
                    String genre = reader.nextLine();
                    book.setGenre(Book.Genre.valueOf(genre));
                    break;
                case 7:
                    System.out.println("Nuevo valor de venta");
                    int saleValue = reader.nextInt();
                    reader.nextLine();
                    book.setSaleValue(saleValue);
                    break;
                default:
                    System.out.println("Por favor digite una opcion valida");
                    break;

            }

        } while (mainOption < 1 || mainOption > 7);
    }

    public void modifyMagazine(){
        int mainOption;
        boolean stopFlag = true;
        Magazine magazine;

        do{
            System.out.println("Inserta el identificador de la revista");
            String bookId = reader.nextLine();
            magazine = (Magazine) library.findBibliographicProduct(bookId);
            if(magazine != null){
                stopFlag = false;
            } else {
                System.out.println("\nid incorrecto");
            }
        } while(stopFlag);

        do{
            System.out.println("\n¿Que quieres modificar?");
            System.out.println("1. id");
            System.out.println("2. Nombre");
            System.out.println("3. Numero de paginas");
            System.out.println("4. Fecha de publicacion");
            System.out.println("5. Categoria");
            System.out.println("6. Valor de suscripción");
            System.out.println("7. Periodicidad de Emision");

            mainOption = reader.nextInt();
            reader.nextLine();

            switch (mainOption) {

                case 1:
                    System.out.println("Nuevo id");
                    String id = reader.nextLine();
                    magazine.setId(id);
                    break;
                case 2:
                    System.out.println("Nuevo nombre");
                    String name = reader.nextLine();
                    magazine.setName(name);
                    break;
                case 3:
                    System.out.println("Nuevo numero de paginas");
                    int totalPages = reader.nextInt();
                    reader.nextLine();
                    magazine.setTotalPages(totalPages);
                    break;
                case 4:
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Nueva fecha de publicación (formato(dd/MM/yyyy))");
                    String publishingDate = reader.nextLine();
                    try {
                        Date date = format.parse(publishingDate);
                        magazine.setPublishingDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Nueva categoria");
                    String category = reader.nextLine();
                    magazine.setCategory(Magazine.Category.valueOf(category));
                    break;
                case 6:
                    System.out.println("Nuevo valor de suscripción");
                    int subscriptionValue = reader.nextInt();
                    reader.nextLine();
                    magazine.setSubscriptionValue(subscriptionValue);
                    break;
                case 7:
                    System.out.println("Nueva periodicidad de emision");
                    String emissionPeriodicity = reader.nextLine();
                    magazine.setEmissionPeriodicity(emissionPeriodicity);
                    break;
                default:
                    System.out.println("Por favor digite una opcion valida");
                    break;

            }

        } while (mainOption < 1 || mainOption > 7);
    }

    public void readingSession(String bibliographicProductId, String userId){
        BibliographicProduct bibliographicProduct = library.findBibliographicProduct(bibliographicProductId);
        User user = library.findUser(userId);

        String option = "";
        int page = 1;

        do{
            System.out.println("Sesion de lectura en progreso");
            System.out.println("\nLeyendo: " + bibliographicProduct.getName());
            System.out.println("\nLeyendo pagina " + page + " de " + bibliographicProduct.getTotalPages());
            System.out.println("\nDigite A para ir a la anterior pagina");
            System.out.println("\nDigite S para ir a la siguiente pagina");
            System.out.println("\nDigite B para volver a la Biblioteca");
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
