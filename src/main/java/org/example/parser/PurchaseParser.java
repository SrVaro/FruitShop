package org.example.parser;

import org.example.model.Product;
import org.example.model.Purchase;

import java.util.Map;

public class PurchaseParser implements Parser<Purchase> {
    @Override
    public Purchase parse(String line,  Map<String, Product> productMap) {
        String[] parts = line.split(",");
        String productName = parts[0].trim();
        int productQuantity = Integer.parseInt(parts[1].trim());

        // Look up product details from the product map
        Product product = productMap.get(productName);
        if (product != null) {
            Purchase purchase = new Purchase(product, productQuantity);
            return(purchase);
        } else {
            // Handle scenario where product is not found in product list
            System.out.println("Warning: Product " + productName + " not found in product list!");
            return null;
        }
    }
}
