package org.kshrd.g3.controller;

import org.kshrd.g3.dao.ProductDAO;
import org.kshrd.g3.dao.ProductDaoImpl;
import org.kshrd.g3.model.Product;
import org.kshrd.g3.model.ProductModel;
import org.kshrd.g3.validation.ProductValidation;
import org.kshrd.g3.view.ProductView;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private static final String ROW_CONFIG_FILE = "src/main/java/org/kshrd/g3/controller/row_config.txt";
    String green="\u001B[32m";
    String red="\u001B[31m";
    String blue="\u001B[34m";
    String reset="\u001B[0m";
    int currentPage = 1;
    int pageSize = 5; // Number of items per page
    int totalPages = 0;

    private final Scanner input = new Scanner(System.in);
    private final ProductDAO productDAO = new ProductDaoImpl();

    private final ProductModel model;
    private final ProductView view;

    public ProductController(ProductModel model, ProductView view) {
        this.model = model;
        this.view = view;
        loadRowConfig();
    }

    private void writeProductToList() {
        String name = ProductValidation.validateProductName();
        double price = ProductValidation.validateUnitPrice();
        int qty = ProductValidation.validateQty();
        Product product = new Product(name, price, qty, LocalDate.now());
        model.addProduct(product);

        System.out.println(blue+"* Inserted Product has been save to temporary List! *"+reset);
        System.out.println(blue+"*** Choose 'Save' Option to insert to Database ***"+reset);
        input.nextLine();
    }

    public void userOption() throws SQLException, IOException, InterruptedException {

        String option;

        do {

            List<Product> products = productDAO.getProductsByPage(currentPage, pageSize);
            totalPages = productDAO.getTotalPages(pageSize);
            int totalProducts = productDAO.getTotalProductsNumber();
            view.renderDisplayMenu(products, currentPage, totalPages, totalProducts);
            option = ProductValidation.validateChooseOption();

            switch (option) {
                case "f":
                    if (currentPage > 1) {
                        currentPage = 1;
                    } else {
                        System.out.println(red+"Already on the first page"+reset);
                    }
                    break;
                case "p":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println(red+"Already on the previous page"+reset);
                    }
                    break;
                case "n":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println(red+"Already on the next page"+reset);
                    }
                    break;
                case "l":
                    if (currentPage < totalPages) {
                        currentPage = totalPages;
                    } else {
                        System.out.println(red+"Already on the last page"+reset);
                    }
                    break;
                case "g":
                    goToSpecifyPage();
                    break;
                case "*":
                    System.out.println("Display");
                    break;
                case "w":
                    writeProductToList();
                    break;
                case "r":
                    viewProductDetails();
                    System.out.print(blue+"Press any key to continue..."+reset);
                    input.nextLine();
                    break;
                case "u":
                    updateProduct();
                    break;
                case "d":
                    deleteProductById();
                    break;
                case "s":
                    searchProduct();
                    break;
                case "se":
                    setRow();
                    break;
                case "sa":
                    insertUnsavedOrUnsavedUpdateProduct();
                    break;
                case "un":
                    view.renderUnsavedInsertedProductListTable(model.getUnSavedInsertedProducts());
                    displayUnsavedProductList();
                    break;
                case "ba":
                    System.out.println(blue+"Backup"+reset);
                    backupProductDataFromDatabase();
                    break;
                case "re":
                    System.out.println(blue+"Restore"+reset);
                    displayDatabaseBackupFileTable();
                    restoreProductDataFromBackup();
                    break;
                case "e":
                    System.out.println(blue+"Thank you so much for using our system..!🙏❤️"+reset);
                    System.out.println(blue+"Exit"+reset);
                    break;
                default:
                    System.out.println(red+"Invalid option"+reset);
                    break;
            }
        } while (!option.equals("e"));
    }


    private void loadRowConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ROW_CONFIG_FILE))) {
            String rowStr = reader.readLine();
            if (rowStr != null && !rowStr.isEmpty()) {
                pageSize = Integer.parseInt(rowStr);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        }
    }

    private void saveRowConfig() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROW_CONFIG_FILE))) {
            writer.write(Integer.toString(pageSize));
        } catch (IOException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        }
    }

    public void setRow() {
        System.out.print(green + "Enter the number of rows you want to set: " + reset);

        // Validate input to ensure it's an integer
        while (!input.hasNextInt()) {
            System.out.println(red + "Invalid input. Please enter a valid number." + reset);
            System.out.print(green + "Enter the number of rows you want to set: " + reset);
            input.next();
        }

        int rows = input.nextInt();

        if (rows > 0) {
            pageSize = rows;
            saveRowConfig(); // Save the row configuration
            System.out.println(blue + "Number of rows set to: " + rows + reset);
        } else {
            System.out.println(red + "Invalid number of rows." + reset);
        }
    }


    private void goToSpecifyPage() {
        System.out.print(green + "Enter the page number you want to go to: " + reset);

        // Validate input to ensure it's an integer
        while (!input.hasNextInt()) {
            System.out.println(red + "Invalid input. Please enter a valid number." + reset);
            System.out.print(green + "Enter the page number you want to go to: " + reset);
            input.next();
        }

        int page = input.nextInt();
        input.nextLine();

        if (page >= 1 && page <= totalPages) {
            currentPage = page;
        } else {
            System.out.println(red + "Invalid page number. Please enter a number between 1 and " + totalPages + reset);
        }
    }


    ///////////////////////////////////////////////////


    private void displayDatabaseBackupFileTable() {
        view.renderDatabaseBackupFileTable();
//        productDAO.resetBackupFileCounter();
    }

    private void restoreProductDataFromBackup() {

        System.out.print(green+"-> Enter Number to restore or 0 to cancel : "+reset);
        int number = input.nextInt();
        input.nextLine();
        if (number == 0) {
            return;
        }
        productDAO.restoreProductData(number);
    }

    private void backupProductDataFromDatabase() throws IOException, InterruptedException {
        productDAO.backupProductData();
    }


    private void updateProduct() {
        System.out.print(green+"-> Input ID to update product : "+reset);
        int id = ProductValidation.validateProductId();
        Product productToUpdate = productDAO.getById(id);
        String name = ProductValidation.validateUpdateProductName();
        double price = ProductValidation.validateUnitPrice();
        int qty = ProductValidation.validateUpdateQty();
        System.out.print(blue+"* Updated Product has been save to temporary List! *"+reset);
        input.nextLine();

        productToUpdate.setName(name);
        productToUpdate.setPrice(price);
        productToUpdate.setQty(qty);
        productToUpdate.setImportedDate(LocalDate.now());

        model.addUnSavedUpdatedProduct(productToUpdate);

    }


    private void deleteProductById() {
        System.out.print(green+"-> Input ID to show product : "+reset);
        int id = ProductValidation.validateProductId();
        Product productDAOById = productDAO.getById(id);
        view.renderProductDetailsTable(productDAOById);
        String option = ProductValidation.validateSelectYOrB();

        switch (option) {
            case "y":
                productDAO.deleteProductById(id);
                break;
            case "b":
                break;
            default:
                System.out.println(red+"Invalid option"+reset);
                break;
        }
    }

    private void searchProduct() {
        String name = ProductValidation.validateSearchProductName();
        List<Product> productsDAOByName = productDAO.getByName(name);
        view.renderProductTable(productsDAOByName);
        int option = ProductValidation.select1Or0ToSearch();

        switch (option) {
            case 1:
                searchProduct();
                break;
            case 0:
                break;
            default:
                System.out.println(red+"Invalid option"+reset);
                break;
        }
    }

    private void viewProductDetails() {
        System.out.print(green+"Enter ID to show product details : "+reset);
        int id = ProductValidation.validateProductId();
        Product product = productDAO.getById(id);
        view.renderProductDetailsTable(product);
    }

    private void insertUnsavedOrUnsavedUpdateProduct() throws SQLException {
        System.out.println(blue+"Do you want to save Unsaved Inserted or Unsaved Updated? Please choose one of them!"+reset);
        String option = ProductValidation.validateInsertUnsavedOrUnsavedUpdateProduct();
        if (option.equalsIgnoreCase("Ui")) {
            if (!model.getUnSavedInsertedProducts().isEmpty()) {
                productDAO.insert(model.getUnSavedInsertedProducts());
                model.getUnSavedInsertedProducts().clear();
                System.out.println(blue+"Unsaved Inserted"+reset);
                insertUnsavedOrUnsavedUpdateProduct();
            } else {
                System.out.println(red+"No unsaved inserted products to save."+reset);
                insertUnsavedOrUnsavedUpdateProduct();
            }
        } else if (option.equalsIgnoreCase("Uu")) {
            if (!model.getUnSavedUpdatedProducts().isEmpty()) {
                model.getUnSavedUpdatedProducts().forEach(updatedProduct -> {
                    Product product = productDAO.getById(updatedProduct.getId());
                    if (product != null) {
                        productDAO.updateProducts(Collections.singletonList(updatedProduct));
                    } else {
                        System.out.println(red+"Cannot update product with id "+reset +blue +updatedProduct.getId() +reset+ red+" because it does not exist in the database"+reset);
                    }
                });
                model.getUnSavedUpdatedProducts().clear();
                insertUnsavedOrUnsavedUpdateProduct();
            } else {
                System.out.println(red+"No unsaved updated products to save."+reset);
                insertUnsavedOrUnsavedUpdateProduct();
            }
        } else {
            System.out.println(blue+"Back to main menu"+reset);
        }
    }

    private void displayUnsavedProductList() {
        String option = ProductValidation.validateDisplayUnsavedProductList();

        switch (option) {
            case "i":
                view.renderUnsavedInsertedProductListTable(model.getUnSavedInsertedProducts());
                displayUnsavedProductList();
                break;
            case "u":
                view.renderUnsavedUpdatedProductListTable(model.getUnSavedUpdatedProducts());
                displayUnsavedProductList();
                break;
            case "b":
                break;
            default:
                System.out.println(red+"Invalid option"+reset);
                break;
        }
    }
}
