package org.kshrd.g3.validation;

import org.kshrd.g3.dao.ProductDaoImpl;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductValidation {
    static String green="\u001B[32m";
    static String red="\u001B[31m";
    static String blue="\u001B[34m";
    static String reset="\u001B[0m";
    static Scanner sc = new Scanner(System.in);

    public static String validateChooseOption() {
        String input;
        String expression = "(f|p|n|l|g|w|r|u|d|s|se|sa|un|ba|re|e|\\*)";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(green+"=> Choose an option() : "+ reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, choose above option"+reset);
            }
        }
        return input;
    }

    public static String validateInsertUnsavedOrUnsavedUpdateProduct() {
        String input;
        String expression = "(ui|uu|b)";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(green+"\"Ui\" for Unsaved Inserted and \"Uu\" for Unsaved Updated and \"B\" for back to main menu : "+reset);

            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, please choose(ui, uu, or b)"+reset);
            }
        }
        return input;
    }

    public static String validateDisplayUnsavedProductList() {
        String input;
        String expression = "(i|u|b)";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(green+"\"I\" for unsaved Insertion and \"U\" for unsaved Update and \"B\" for back to main menu : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, please choose(ui, uu, or b)"+reset);
            }
        }
        return input;
    }


    public static int select1Or0ToSearch(){
        String input;
        Pattern pattern = Pattern.compile("[0-1]");
        while (true) {
            System.out.print(green+"Press 1 to search again and 0 to cancel : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, choose only 0 or 1"+reset);
            }
        }
        return Integer.parseInt(input);
    }

    public static int select1Or0ToRestore(){
        String input;
        Pattern pattern = Pattern.compile("[0-1]");
        while (true) {
            System.out.print(green+"Press 1 to restore and 0 to cancel : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, choose only 0 or 1"+reset);
            }
        }
        return Integer.parseInt(input);
    }

    public static String validateSelectYOrB(){
        String input;
        Pattern pattern = Pattern.compile("\\b(y|b)\\b", Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(green+"-> Choose Y or B : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid option, choose only Y or B"+reset);
            }
        }
        return input;
    }

    public static String validateSearchProductName(){
        String input;
        Pattern pattern = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");
        while (true) {
            System.out.print(green+"-> Input Product's name to search : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, cannot enter number!"+reset);
            }
        }
        return input;
    }

    public static String validateProductName(){
        String input;
        Pattern pattern = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");
        //Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:\\d+[a-zA-Z]*)?$");
        while (true) {
            System.out.print(blue+"-> Input Product Name : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, cannot enter number!"+reset);
            }
        }
        return input;
    }

    public static double validateUnitPrice(){
        String input;
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        while (true) {
            System.out.print(blue+"-> Input Product Unit Price : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, you can enter only number!"+reset);
            }
        }
        return Double.parseDouble(input);

    }

    public static int validateQty(){
        //input only number
        String input;
        Pattern pattern = Pattern.compile("\\d+");
        while (true) {
            System.out.print(blue+"-> Input Product Qty : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, please input only integer number!"+reset);
            }
        }
        return Integer.parseInt(input);
    }

    public static String validateUpdateProductName(){
        String input;
        //Pattern pattern = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:\\d+[a-zA-Z]*)?$");

        while (true) {
            System.out.print(blue+"-> Change Product Name to : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, cannot enter number!"+reset);
            }
        }
        return input;
    }
    public static int validateUpdateQty(){
        //input only number
        String input;
        Pattern pattern = Pattern.compile("\\d+");
        while (true) {
            System.out.print(blue+"-> Change Product Qty to : "+reset);
            input = sc.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println(red+"Invalid input, please input only integer number!"+reset);
            }
        }
        return Integer.parseInt(input);
    }


    public static int validateProductId() {
        ProductDaoImpl productDao = new ProductDaoImpl();
        List<Integer> ids = productDao.getAllRecord();

        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (!ids.contains(input)) {
                    System.out.println(red + "Error: Product ID not found." + reset);
                    System.out.println(green + "*** ID List *** :" + reset);
                    for (int pId : ids) {
                        System.out.print(pId + "\t");
                        System.out.println("Enter again: ");
                    }
                    System.out.println("\n" + green + "Please enter a valid ID: " + reset);
                } else {
                    System.out.println(blue + "Product's id is : " + input + reset);
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println(red + "Error: Please enter a valid integer." + reset);
            }
        }
    }

}