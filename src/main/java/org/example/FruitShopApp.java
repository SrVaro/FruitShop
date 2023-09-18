package org.example;


import org.example.model.*;
import org.example.offers.FreeProductOffer;
import org.example.offers.Offer;
import org.example.offers.QuantityDiscountOffer;
import org.example.offers.SpendDiscountOffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class FruitShopApp
{

    public static void main( String[] args )
    {
        CSVParser parser = new CSVParser();

        Map<String, Product> productMap = new HashMap<>();
        productMap = parser.ReadProductsFile("resources/products.txt");

        Order order;

        order = parser.ReadPurchaseFile("resources/purchase.txt", productMap);

        List<Offer> activeOffers = Arrays.asList(
                new QuantityDiscountOffer(productMap.get("Apple"), 3, 2),
                new FreeProductOffer(productMap.get("Pear"), 2, productMap.get("Orange")),
                new SpendDiscountOffer(productMap.get("Pear"), 4.0, 1.0)
        );

        order.ApplyOffers(activeOffers);

        CalculateReceipt(order);

    }

    private static void CalculateReceipt(Order order) {

        System.out.println(order.getPurchaseTotalPrice());
        System.out.println(order.getPurchases());
        System.out.println(order.getTotalDiscount());
        System.out.println(order.getAppliedOffersDescriptions());
    }

}
