package org.kshrd.g3.view;

import org.kshrd.g3.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.File;
import java.util.List;

public class ProductView {
    String green="\u001B[32m";
    String red="\u001B[31m";
    String blue="\u001B[34m";
    String cyan="\u001B[36m";
    String yellow="\u001B[33m";
    String reset="\u001B[0m";
    String purple="\u001B[35m";

    public void renderDisplayMenu(List<Product> products, int currentPage, int totalPages, int totalProducts) throws InterruptedException {

        renderProductListTable(products, currentPage, totalPages, totalProducts);

        Table tableOption = new Table(6, BorderStyle.DESIGN_PAPYRUS, ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);


        tableOption.setColumnWidth(0, 16, 25);
        tableOption.setColumnWidth(1, 16, 25);
        tableOption.setColumnWidth(2, 16, 25);
        tableOption.setColumnWidth(3, 16, 25);
        tableOption.setColumnWidth(4, 16, 25);
        tableOption.setColumnWidth(5, 16, 25);

        tableOption.addCell(green+"F) First"+reset);
        tableOption.addCell(green+"P) Previous"+reset);
        tableOption.addCell(green+"N) Next"+reset);
        tableOption.addCell(green+"L) Last"+reset);
        tableOption.addCell(green+"G) Go to"+reset);
        tableOption.addCell("");

        tableOption.addCell(green+"*) Display"+reset);
        tableOption.addCell(green+"W) Write"+reset);
        tableOption.addCell(green+"R) Read"+reset);
        tableOption.addCell(green+"U) Update"+reset);
        tableOption.addCell(green+"D) Delete"+reset);
        tableOption.addCell(green+"S) Search"+reset);
        tableOption.addCell(green+"Se) Set row"+reset);
        tableOption.addCell(green+"Sa) Save"+reset);
        tableOption.addCell(green+"Un) Unsaved"+reset);
        tableOption.addCell(green+"Ba) Backup"+reset);
        tableOption.addCell(green+"Re) Restore"+reset);
        tableOption.addCell(green+"E) Exit"+reset);

        System.out.println(tableOption.render());

        System.out.println(green+"--------------------------"+reset);
    }



    public void renderUnsavedInsertedProductListTable(List<Product> unSavedProducts) {

        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 20, 25);
        table.setColumnWidth(1, 25, 25);
        table.setColumnWidth(2, 25, 25);
        table.setColumnWidth(3, 20, 25);

        table.addCell(purple+"Unsaved Product List"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 4);
        table.addCell(cyan+"Name"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Unit Price"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Qty"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Imported Date"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        if (unSavedProducts.isEmpty()) {
            table.addCell(red+"No data available"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 4);
        } else {
            unSavedProducts.forEach(product -> {
                table.addCell(yellow+product.getName()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+"$"+product.getPrice()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(yellow+product.getQty()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+product.getImportedDate()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            });
        }

        System.out.println(table.render());
    }


    public void renderProductListTable(List<Product> products, int currentPage, int totalPages, int totalProducts) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 20, 25);
        table.setColumnWidth(1, 25, 25);
        table.setColumnWidth(2, 25, 25);
        table.setColumnWidth(3, 20, 25);
        table.setColumnWidth(4, 20, 25);

        table.addCell(purple+"Product List"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
        table.addCell(cyan+"ID"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Name"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Unit Price"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Qty"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Imported Date"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        if (!products.isEmpty()) {
            products.forEach(product -> {
                table.addCell(green+product.getId()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(yellow+product.getName()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+"$"+product.getPrice()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(yellow+product.getQty()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+product.getImportedDate()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            });
        } else {
            table.addCell(red+"No data available"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
        }

        table.addCell(blue+"Page " + currentPage + " of " + totalPages+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 2);
        table.addCell(blue+"Total Records : "+ totalProducts + reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 3);

        System.out.println(table.render());
    }


    public void renderProductDetailsTable(Product product) {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        table.setColumnWidth(0, 40, 80);
        table.addCell(purple+"Product Detail"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);

        table.addCell(" ".repeat(4) + cyan+"ID : "+reset + product.getId());
        table.addCell(" ".repeat(4) +cyan+ "Name : " +reset+ product.getName());
        table.addCell(" ".repeat(4) + cyan+"Unit Price : " +reset+ product.getPrice());
        table.addCell(" ".repeat(4) + cyan+"Qty : "+reset + product.getQty());
        table.addCell(" ".repeat(4) + cyan+"Imported Date : "+reset + product.getImportedDate());

        System.out.println(table.render());
    }

    public void renderProductTable(List<Product> productDAOByName) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 20, 25);
        table.setColumnWidth(1, 25, 25);
        table.setColumnWidth(2, 25, 25);
        table.setColumnWidth(3, 20, 25);
        table.setColumnWidth(4, 20, 25);

        table.addCell(cyan+"ID"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Name"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Unit Price"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Qty"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyan+"Imported Date"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        productDAOByName.forEach(product -> {
            table.addCell(blue+product.getId()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(green+product.getName()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(blue+"$"+product.getPrice()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(green+product.getQty()+reset,new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(blue+product.getImportedDate()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        });

        System.out.println(table.render());
    }

    public void renderUnsavedUpdatedProductListTable(List<Product> unSavedUpdatedProducts) {
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 20, 25);
        table.setColumnWidth(1, 25, 25);
        table.setColumnWidth(2, 25, 25);
        table.setColumnWidth(3, 20, 25);

        table.addCell(purple+"Unsaved Updated Product List"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 5);
        table.addCell(green+"Name"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(blue+"Unit Price"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(green+"Qty"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(blue+"Imported Date"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
        if (unSavedUpdatedProducts.isEmpty()){
            table.addCell(red+"No data available"+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER), 4);
        } else {
            unSavedUpdatedProducts.forEach(product -> {
                table.addCell(blue+product.getName()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+"$" + product.getPrice()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(blue+product.getQty()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
                table.addCell(green+product.getImportedDate()+reset, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            });
        }


        System.out.println(table.render());
    }

    public void renderDatabaseBackupFileTable() {
        String homeDirectory = System.getProperty("user.home");
        String backupFolder = homeDirectory + File.separator + "backup-product-data";
        File file = new File(backupFolder);
        File[] files = file.listFiles();

        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 8, 80);
        table.setColumnWidth(1, 50, 80);

        table.addCell("Backup List", new CellStyle(CellStyle.HorizontalAlign.CENTER), 2);

        if (files != null) {
            int id = 1;
            for (File f : files) {
                if (f.isFile()) {
                    table.addCell(String.valueOf(id), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    table.addCell(f.getName(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    id++;
                }
            }
        }

        System.out.println(table.render());
    }
}
