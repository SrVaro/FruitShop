package org.example.offers;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Purchase;

import java.util.*;

public class FreeProductOffer implements Offer {
    private Product targetProduct;  // Product triggering the offer (e.g., Pear)
    private int targetQuantity;    // Quantity of the target product (e.g., 2)
    private Product freeProduct;   // Product given for free (e.g., Orange)

    @Override
    public void applyOffer(List<Purchase> purchases, Order order) {
        for (Purchase purchase : purchases) {
            if (purchase.getProduct().equals(targetProduct)) {
                int freeItems = purchase.getQuantity() / targetQuantity;
                order.AddDiscount(freeProduct.getPrice() * freeItems);
                order.AddAppliedOfferDescription("You get " + freeItems + " " + freeProduct + " for free!");
            }
        }
    }

    public FreeProductOffer(Product targetProduct, int targetQuantity, Product freeProduct) {
        this.targetProduct = targetProduct;
        this.targetQuantity = targetQuantity;
        this.freeProduct = freeProduct;
    }
}
