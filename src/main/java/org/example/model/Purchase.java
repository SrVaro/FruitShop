package org.example.model;

public class Purchase {

    private Product product;

    private int quantity;

    private Double purchasePriceSum;

    public Purchase(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.purchasePriceSum = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Double getPurchasePriceSum() {
        return purchasePriceSum;
    }

    public void setPurchasePriceSum(Double purchasePriceSum) {
        this.purchasePriceSum = purchasePriceSum;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
