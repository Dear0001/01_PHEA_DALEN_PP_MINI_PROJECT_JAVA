package org.kshrd.g3;

import org.kshrd.g3.controller.ProductController;
import org.kshrd.g3.model.ProductModel;
import org.kshrd.g3.view.ProductView;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        ProductView view = new ProductView();
        ProductModel model = new ProductModel();
        ProductController productController = new ProductController(model, view);
        productController.userOption();
    }
}