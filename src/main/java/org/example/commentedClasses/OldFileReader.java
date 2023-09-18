package org.example.commentedClasses;

import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldFileReader {

    public Map<String, Product> ReadProductsFile(String path ) {

        Map<String, Product> productMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            String line;
            // Header Line (Not necessary right now
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String productName = parts[0].trim();
                double productPrice = Double.parseDouble(parts[1].trim());

                Product product = new Product(productName, productPrice);
                productMap.put(productName, product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productMap;
    }

    public Order ReadPurchaseFile(String path, Map<String, Product> productMap) {

        List<Purchase> purchaseList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            String line;
            // Header Line (We skip it as is not necessary)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String productName = parts[0].trim();
                int productQuantity = Integer.parseInt(parts[1].trim());

                // Look up product details from the product map
                Product product = productMap.get(productName);
                if (product != null) {
                    Purchase purchase = new Purchase(product, productQuantity);
                    purchaseList.add(purchase);
                } else {
                    // Handle scenario where product is not found in product list
                    System.out.println("Warning: Product " + productName + " not found in product list!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Order(purchaseList);
    }
}
