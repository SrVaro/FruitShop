package org.example.model.offers;
import org.example.model.Order;
import org.example.model.Purchase;

import java.util.*;

public interface Offer {
    void applyOffer(List<Purchase> purchases, Order order);
}
