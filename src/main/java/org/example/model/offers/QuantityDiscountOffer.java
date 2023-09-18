package org.example.model.offers;

import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Purchase;

import java.util.List;

public class QuantityDiscountOffer implements Offer {
    private Product product;
    private int buyQuantity;
    private int payQuantity;

    @Override
    public void applyOffer(List<Purchase> purchases, Order order) {
        for (Purchase purchase : purchases) {
            if (purchase.getProduct().equals(product)) {
                int discountedItems = purchase.getQuantity() / buyQuantity;
                double discountAmount = discountedItems * product.getPrice() * (buyQuantity - payQuantity);
                order.AddDiscount(discountAmount);
                order.AddAppliedOfferDescription("Discount on " + product.getName() + ": -" + discountAmount);
            }
        }
    }

    public QuantityDiscountOffer(Product product, int buyQuantity, int payQuantity) {
        this.product = product;
        this.buyQuantity = buyQuantity;
        this.payQuantity = payQuantity;
    }
}
