package org.example.model.offers;

import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Purchase;

import java.util.List;

public class SpendDiscountOffer implements Offer {
    private Product product;
    private double spendThreshold;
    private double discountAmount;

    @Override
    public void applyOffer(List<Purchase> purchases, Order order) {
        for (Purchase purchase : purchases) {
            if (purchase.getProduct().equals(product)) {
                double totalSpend = purchase.getQuantity() * product.getPrice();
                int discountTimes = (int) (totalSpend / spendThreshold);
                double totalDiscount = discountTimes * discountAmount;
                if(totalDiscount != 0) {
                    order.AddDiscount(totalDiscount);
                    order.AddAppliedOfferDescription("Spend discount on " + product.getName() + ": -" + totalDiscount);
                }
            }
        }
    }

    public SpendDiscountOffer(Product product, double spendThreshold, double discountAmount) {
        this.product = product;
        this.spendThreshold = spendThreshold;
        this.discountAmount = discountAmount;
    }
}
