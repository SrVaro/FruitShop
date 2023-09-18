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
        Map<String, Product> productMap = new HashMap<>();

        // Se lee el archivo donde se encuentras los productos
        FileReader<Product> productReader = new FileReader<>(new ProductParser());
        List<Product> products = productReader.readFromFile("resources/products.txt", null);
        productMap = products.stream().collect(toMap(Product::getName, p -> p));

        // Con las lista de productos se lee el archivo de la compra y se coteja que cada producto exista
        FileReader<Purchase> orderReader = new FileReader<>(new PurchaseParser());
        List<Purchase> purchases = orderReader.readFromFile("resources/purchase.txt", productMap);

        // Se crea la orden con todos los pedidos
        Order order = new Order(purchases);

        // Se crea una lista de todos las ofertas activas
        List<Offer> activeOffers = Arrays.asList(
                new QuantityDiscountOffer(productMap.get("Apple"), 3, 2),
                new FreeProductOffer(productMap.get("Pear"), 2, productMap.get("Orange")),
                new SpendDiscountOffer(productMap.get("Pear"), 4.0, 1.0)
        );

        order.ApplyOffers(activeOffers);

        // Se muestra por consola el ticket de compra
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
