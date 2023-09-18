package org.example.model;
import org.example.model.offers.Offer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Order {
    private List<Purchase> purchases;
    private double purchaseNumber = 0;
    private double purchaseTotalPrice = 0;
    private Double totalDiscount = 0.0;
    private List<String> appliedOffersDescriptions = new ArrayList<>();

    public Order(List<Purchase> purchases) {
        this.purchases = purchases;
        for(Purchase item : purchases) {
            purchaseTotalPrice += item.getPurchasePriceSum();
        }



        purchaseNumber = purchases.size();
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public double getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(double purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public List<String> getOffersApplied() {
        return appliedOffersDescriptions;
    }

    public void setOffersApplied(List<String> offersApplied) {
        this.appliedOffersDescriptions = offersApplied;
    }

    public Double getPurchaseTotalPrice() {
        return purchaseTotalPrice;
    }

    public void setPurchaseTotalPrice(Double purchaseTotalPrice) {
        this.purchaseTotalPrice = purchaseTotalPrice;
    }

    public List<String> GetProductList(){
        List<String> productList = new ArrayList<>();
        for(Purchase item : purchases) {
            productList.add(item.getProduct().getName());
        }
        return productList;
    }

    public void AddDiscount(Double discount) {
        this.totalDiscount += discount;
        this.purchaseTotalPrice -= discount;
    }

    public void AddAppliedOfferDescription(String description) {
        this.appliedOffersDescriptions.add(description);
    }

    public void ApplyOffers(List<Offer> activeOffers) {
        for (Offer offer : activeOffers) {
            offer.applyOffer(purchases, this);
        }
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public List<String> getAppliedOffersDescriptions() {
        return appliedOffersDescriptions;
    }

    public void setAppliedOffersDescriptions(List<String> appliedOffersDescriptions) {
        this.appliedOffersDescriptions = appliedOffersDescriptions;
    }

    @Override
    public String toString() {
        return "Order {" +
                "purchases=" + purchases +
                ", total=" + purchaseNumber +
                ", offersApplied=" + appliedOffersDescriptions +
                '}';
    }


}
