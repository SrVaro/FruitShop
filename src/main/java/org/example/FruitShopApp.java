package org.example;


import org.example.fileReader.FileReader;
import org.example.model.*;
import org.example.model.offers.FreeProductOffer;
import org.example.model.offers.Offer;
import org.example.model.offers.QuantityDiscountOffer;
import org.example.model.offers.SpendDiscountOffer;
import org.example.parser.ProductParser;
import org.example.parser.PurchaseParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Hello world!
 *
 */
public class FruitShopApp
{

    public static void main( String[] args )
    {
        //FileReader parser = new FileReader();

        Map<String, Product> productMap = new HashMap<>();

        FileReader<Product> productReader = new FileReader<>(new ProductParser());
        List<Product> products = productReader.readFromFile("resources/products.txt", null);

        productMap = products.stream().collect(toMap(Product::getName, p -> p));

        FileReader<Purchase> orderReader = new FileReader<>(new PurchaseParser());
        List<Purchase> purchases = orderReader.readFromFile("resources/purchase.txt", productMap);

        Order order = new Order(purchases);

        List<Offer> activeOffers = Arrays.asList(
                new QuantityDiscountOffer(productMap.get("Apple"), 3, 2),
                new FreeProductOffer(productMap.get("Pear"), 2, productMap.get("Orange")),
                new SpendDiscountOffer(productMap.get("Pear"), 4.0, 1.0)
        );

        order.ApplyOffers(activeOffers);

        ShowReceipt(order);

    }

    private static void ShowReceipt(Order order) {

        System.out.printf("\n The total price of the order is: %.2f€ \n", order.getPurchaseTotalPrice());
        for (Purchase item: order.getPurchases()) {
            System.out.println(item.getProduct().getName() + " " + item.getProduct().getPrice() + "€");
        }

        System.out.println("\nDiscounts:");
        System.out.println(order.getAppliedOffersDescriptions());
        System.out.println("Thats get you a total discount of: " + order.getTotalDiscount() + "€" + "\n");

        System.out.println("Thank you for your visit, come back soon! :D");
    }

}
