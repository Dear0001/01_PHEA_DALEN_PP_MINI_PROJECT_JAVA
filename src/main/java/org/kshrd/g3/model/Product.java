package org.kshrd.g3.model;

import java.time.LocalDate;

public class Product {

    private int id;
    private String name;
    private double price;
    private int qty;
    private LocalDate importedDate;

    public Product() {
    }

    public Product(int id, String name, double price, int qty, LocalDate importedDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.importedDate = importedDate;
    }

    public Product(String name, double price, int qty, LocalDate importedDate) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.importedDate = importedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public LocalDate getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(LocalDate importedDate) {
        this.importedDate = importedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", importedDate=" + importedDate +
                '}';
    }
}
